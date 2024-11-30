package com.example.dummyjson.config;

import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

@SpringBootTest
public class ValidationConfigTest {

    @Autowired
    private Validator validator;

    @Test
    public void testValidatorBean() {
        // Check if the validator bean is correctly created
        assertNotNull(validator, "Validator bean should not be null");
    }
}
