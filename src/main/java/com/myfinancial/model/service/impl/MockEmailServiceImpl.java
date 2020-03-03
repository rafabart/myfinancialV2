package com.myfinancial.model.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;

@Slf4j
public class MockEmailServiceImpl extends AbstractEmailServiceImpl {

    @Override
    public void sendEmail(final SimpleMailMessage message) {

        log.info("Simulando envio de email...");
        log.info(message.toString());
        log.info("Email enviado!");
    }
}
