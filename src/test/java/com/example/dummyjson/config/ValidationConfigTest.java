package com.example.dummyjson.config;

import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ValidationConfigTest {

    @Test
    void testValidatorBeanCreation() {
        ValidationConfig validationConfig = new ValidationConfig();
        Validator validator = validationConfig.validator(); // Create Validator instance

        assertNotNull(validator, "Validator should not be null");
        // Additional tests can validate the functionality of the Validator
    }
}
