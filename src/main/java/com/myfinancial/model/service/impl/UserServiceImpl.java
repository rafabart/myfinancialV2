package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.enums.ProfileType;
import com.myfinancial.model.domain.request.UserRequest;
import com.myfinancial.model.domain.response.UserResponse;
import com.myfinancial.model.exception.AuthorizationException;
import com.myfinancial.model.exception.EmailExistingException;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.repository.CustomerRepository;
import com.myfinancial.model.security.UserSpringSecurity;
import com.myfinancial.model.service.EmailService;
import com.myfinancial.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserResponse findById(final Long id) {

        final Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário"));

        return new UserResponse(customer);
    }


    public UserResponse findByIdAndUser(final Long id) {

        final Customer customerAuthenticated = getAuthenticatedUser();

        final Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário"));

        if (!customer.getId().equals(customerAuthenticated.getId())) {
            throw new AuthorizationException();
        }

        return new UserResponse(customer);
    }


    public List<UserResponse> findAll() {

        List<Customer> customerList = customerRepository.findAll();

        return customerList.stream().map(user -> new UserResponse(user)).collect(Collectors.toList());
    }


    @Transactional
    public void delete(final Long id) {

        findById(id);

        customerRepository.deleteById(id);
    }


    @Transactional
    public Long create(final UserRequest userRequest) {

        if (customerRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new EmailExistingException();
        }

        Customer customer = new Customer(userRequest);
        customer.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));

        final Long id = customerRepository.save(customer).getId();

//        try {
            emailService.sendAccountCreatedConfirmationEmail(userRequest);
//        } catch (Exception e) {
//            throw new EmailSenderException("Não foi possível enviar o email, o novo usuário não foi criado!");
//        }

        return id;
    }


    @Transactional
    public void update(final Long id, final UserRequest userRequest) {

        findById(id);

        Customer customer = getAuthenticatedUser();

        if (customer.getId() != id && !customer.getProfileList().contains(ProfileType.ADMIN.getCod())) {
            throw new AuthorizationException();
        }

        if (!customer.getProfileList().contains(ProfileType.ADMIN.getCod()) && userRequest.getProfileListSring().contains(ProfileType.ADMIN.getName())) {
            throw new AuthorizationException();
        }

        Optional<Customer> userOptional = customerRepository.findByEmail(userRequest.getEmail());

        if (userOptional.isPresent() && userOptional.get().getId() != id) {
            throw new EmailExistingException();
        }

        if (!customer.getPassword().equals(userRequest.getPassword())) {
            userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        }

        customer = customerRepository.getOne(id);
        customer.updateUser(userRequest);

        customerRepository.save(customer);
    }


    public Customer getAuthenticatedUser() {


        try {
            final UserSpringSecurity userSpringSecurity = (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            final Customer customer = new Customer(userSpringSecurity);

            return customer;

        } catch (Exception e) {
            throw new AuthorizationException();
        }
    }
}
