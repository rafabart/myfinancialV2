package com.myfinancial.model.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Arrays;

@Slf4j
public class SmtpEmailServiceImpl extends AbstractEmailServiceImpl {

    @Autowired
    private MailSender mailSender;

    @Override
    public void sendEmail(SimpleMailMessage message) {

        log.info("Enviando email para...");
        log.info(Arrays.toString(message.getTo()));
        mailSender.send(message);
        log.info("Email enviado!");
    }
}
