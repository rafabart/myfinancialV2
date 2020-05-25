package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.enums.ProfileType;
import com.myfinancial.model.domain.request.CustomerRequest;
import com.myfinancial.model.domain.response.CustomerResponse;
import com.myfinancial.model.exception.AuthorizationException;
import com.myfinancial.model.exception.EmailExistingException;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.mapper.CustomerMapper;
import com.myfinancial.model.repository.CustomerRepository;
import com.myfinancial.model.security.UserSpringSecurity;
import com.myfinancial.model.service.CustomerService;
import com.myfinancial.model.service.EmailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public CustomerResponse findById(final Long id) {

        final Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário"));

        return customerMapper.toReponse(customer);
    }


    public CustomerResponse findByIdAndCustomer(final Long id) {

        final Customer customerAuthenticated = getAuthenticatedUser();

        final Customer customer = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário"));

        if (!customer.getId().equals(customerAuthenticated.getId())) {
            throw new AuthorizationException();
        }

        return customerMapper.toReponse(customer);
    }


    public List<CustomerResponse> findAllByUser() {

        List<Customer> customerList = customerRepository.findAll();

        return customerMapper.toResponseList(customerList);
    }


    @Transactional
    public void delete(final Long id) {

        findById(id);

        customerRepository.deleteById(id);
    }


    @Transactional
    public Long create(final CustomerRequest customerRequest) {

        customerRepository.findByEmail(customerRequest.getEmail()).ifPresent(customer -> new EmailExistingException());

        Customer customer = customerMapper.to(customerRequest);

        final String generatedString = RandomStringUtils.randomAlphanumeric(10);
        customer.setPassword(bCryptPasswordEncoder.encode(generatedString));

        final Long id = customerRepository.save(customer).getId();

        emailService.sendAccountCreatedConfirmationEmail(customerRequest, generatedString);

        return id;
    }


    @Transactional
    public void update(final CustomerRequest customerRequest) {

        findById(customerRequest.getId());

        Customer customer = getAuthenticatedUser();

        if (!customer.getId().equals(customerRequest.getId()) && !customer.getProfileType().equals((ProfileType.ADMIN))) {
            throw new AuthorizationException();
        }

        if (!customer.getProfileType().equals(ProfileType.ADMIN) && customerRequest.getProfileType().equals(ProfileType.ADMIN.getName())) {
            throw new AuthorizationException();
        }

        Optional<Customer> userOptional = customerRepository.findByEmail(customerRequest.getEmail());

        if (userOptional.isPresent() && !userOptional.get().getId().equals(customerRequest.getId())) {
            throw new EmailExistingException();
        }

        customer = customerRepository.getOne(customerRequest.getId());
        customerMapper.toUpdate(customer, customerRequest);

        customerRepository.save(customer);
    }


    public Customer getAuthenticatedUser() {

        try {
            final UserSpringSecurity userSpringSecurity = (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return customerRepository.findByEmail(userSpringSecurity.getEmail()).orElseThrow(() -> new ObjectNotFoundException("Usuário"));

        } catch (Exception e) {
            throw new AuthorizationException();
        }
    }
}
