package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.exception.EmailSenderException;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.repository.CustomerRepository;
import com.myfinancial.model.service.AuthService;
import com.myfinancial.model.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();


    @Transactional
    public void sendNewPassword(final String email) {

        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException("Email"));

        String newPass = newPassword();
        customer.setPassword(bCryptPasswordEncoder.encode(newPass));

        customerRepository.save(customer);
        try {
            emailService.sendNewPasswordEmail(customer, newPass);
        } catch (Exception e) {
            throw new EmailSenderException("Não foi possível enviar o email, não foi criada a nova senha!");
        }
    }


    private String newPassword() {

        char[] vet = new char[10];

        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }

        return new String(vet);

    }


    private char randomChar() {

        int opt = random.nextInt(3);

        //gera um digito
        if (opt == 0) {
            return (char) (random.nextInt(10) + 48);
            //gera letra maiuscula
        } else if (opt == 1) {
            return (char) (random.nextInt(26) + 65);
            // gera letra minuscula
        } else {
            return (char) (random.nextInt(10) + 97);
        }
    }
}
