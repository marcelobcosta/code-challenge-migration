package com.example.dummyjson.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    // Bean for Validator creation
    @Bean
    public Validator validator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            return factory.getValidator(); // Return Validator instance
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Validator instance", e);
        }
    }
}
