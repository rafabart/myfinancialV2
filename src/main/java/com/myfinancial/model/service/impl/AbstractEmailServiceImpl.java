package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.request.UserRequest;
import com.myfinancial.model.service.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailServiceImpl implements EmailService {

    @Value("${email.sender}")
    private String sender;

    @Value("${email.app_link_access}")
    private String appLinkAccess;


    public void sendAccountCreatedConfirmationEmail(final UserRequest userRequest) {
        final SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromPurchase(userRequest);
        sendEmail(simpleMailMessage);
    }


    public void sendNewPasswordEmail(final Customer customer, final String newPassword) {
        final SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromNewPassword(customer, newPassword);
        sendEmail(simpleMailMessage);
    }


    protected SimpleMailMessage prepareSimpleMailMessageFromNewPassword(final Customer customer, final String newPassword) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(customer.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("\nMyFinancial: Solicitação de nova senha!\n");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));

        StringBuffer text = new StringBuffer();
        text.append("\nOlá " + customer.getName() + "!");
        text.append("\nSegue abaixo sua nova senha para acesso ao MyFinancial!\n");
        text.append("\nEmail de acesso: " + customer.getEmail() + "\n");
        text.append("Nova senha: " + newPassword + "\n");
        text.append("\nPara acessar sua conta use o link: http://localhost:8080/login");

        simpleMailMessage.setText(text.toString());

        return simpleMailMessage;
    }


    protected SimpleMailMessage prepareSimpleMailMessageFromPurchase(final UserRequest userRequest) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(userRequest.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("MyFinancial: Acesso liberado!");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));

        StringBuffer text = new StringBuffer();
        text.append("\nParabéns " + userRequest.getName() + "!\n");
        text.append("\nSeu acesso ao MyFinancial já esta liberado!\n");
        text.append("Email de acesso: " + userRequest.getEmail() + "\n");
        text.append("Senha: " + userRequest.getPassword() + "\n");
        text.append("\nPara acessar sua conta use o link: http://localhost:8080/login");

        simpleMailMessage.setText(text.toString());
        return simpleMailMessage;
    }
}
