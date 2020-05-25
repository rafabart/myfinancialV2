package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.request.NewPasswordRequest;
import com.myfinancial.model.exception.EmailSenderException;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.exception.PasswordMatchException;
import com.myfinancial.model.repository.CustomerRepository;
import com.myfinancial.model.service.AuthService;
import com.myfinancial.model.service.CustomerService;
import com.myfinancial.model.service.EmailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmailService emailService;


    @Transactional
    public void sendNewPassword(final String email) {

        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Email"));

        String newPass = RandomStringUtils.randomAlphanumeric(10);
        customer.setPassword(bCryptPasswordEncoder.encode(newPass));

        customerRepository.save(customer);
        try {
            emailService.sendNewPasswordEmail(customer, newPass);
        } catch (Exception e) {
            throw new EmailSenderException("Não foi possível enviar o email, não foi criada a nova senha!");
        }
    }

    @Transactional
    public void changePassword(final NewPasswordRequest newPasswordRequest) {

        if (!newPasswordRequest.getConfirmPassword().equals(newPasswordRequest.getPassword())) {
            throw new PasswordMatchException();
        }

        Customer customer = customerService.getAuthenticatedUser();
        customer.setPassword(bCryptPasswordEncoder.encode(newPasswordRequest.getPassword()));

        customerRepository.save(customer);
    }
}