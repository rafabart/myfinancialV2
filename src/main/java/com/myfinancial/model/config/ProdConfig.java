package com.myfinancial.model.config;

import com.myfinancial.model.service.DataInitializer;
import com.myfinancial.model.service.EmailService;
import com.myfinancial.model.service.impl.SmtpEmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig {

    @Autowired
    private DataInitializer dataInitializer;


    @Bean
    public void InstantiateTestDatabase() {
        dataInitializer.run();
    }


    @Bean
    public EmailService emailService() {
        return new SmtpEmailServiceImpl();
    }
}
