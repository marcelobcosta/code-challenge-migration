package com.example.dummyjson.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ValidationConfigTest {

    @Test
    public void testValidatorBean() {
        // Criando a fábrica de validadores e o validador
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // Verificando se o validador não é nulo
        assertNotNull(validator, "Validator bean should not be null");
    }
}