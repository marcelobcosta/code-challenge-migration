package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test") // Use the test profile configuration
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    // Test fetching all products from the API
    @Test
    void testGetAllProducts() {
        // Call the service to get all products
        Mono<List<Product>> productsMono = productService.getAllProducts();
        // Block the Mono to retrieve the result (not recommended for production)
        List<Product> products = productsMono.block();

        // Assert that the list of products is not null
        assertNotNull(products);
        // Additional assertions can be added here to validate the response
    }

    // Test fetching a product by its ID from the API
    @Test
    void testGetProductById() {
        Long testId = 1L; // A valid test product ID
        // Call the service to get a product by ID
        Mono<Product> productMono = productService.getProductById(testId);
        // Block the Mono to retrieve the result
        Product product = productMono.block();

        // Assert that the product is not null
        assertNotNull(product);
        // Additional assertions can be added here to validate product attributes
    }
}
