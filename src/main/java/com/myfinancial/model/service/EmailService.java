package com.myfinancial.model.service;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.request.CustomerRequest;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendAccountCreatedConfirmationEmail(final CustomerRequest customerRequest, final String generatedString);

    void sendNewPasswordEmail(final Customer customer, final String newPassword);

    void sendEmail(final SimpleMailMessage message);
}
