package com.myfinancial.model.config;

import com.myfinancial.model.service.EmailService;
import com.myfinancial.model.service.impl.SmtpEmailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Bean
    public EmailService emailService() {
        return new SmtpEmailServiceImpl();
    }
}
