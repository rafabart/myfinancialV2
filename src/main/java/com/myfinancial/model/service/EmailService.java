package com.myfinancial.model.service;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.request.UserRequest;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendAccountCreatedConfirmationEmail(final UserRequest userRequest);

    void sendNewPasswordEmail(final Customer customer, final String newPassword);

    void sendEmail(final SimpleMailMessage message);
}
