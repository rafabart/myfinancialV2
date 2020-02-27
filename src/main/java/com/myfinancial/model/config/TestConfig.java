package com.myfinancial.model.config;

import com.myfinancial.model.service.DataInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DataInitializer dataInitializer;


    @Bean
    public void InstantiateTestDatabase() {
        dataInitializer.run();
    }
}
