package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        List<Product> products = productService.getAllProducts().block(); // Transform Mono<List<Product>> into List<Product>

        // Assert: Verify the results
        assertNotNull(products, "The product list should not be null");
        assertFalse(products.isEmpty(), "The product list should not be empty");
        assertTrue(products.size() > 0, "There should be at least one product");
    }

    @Test
    public void testGetProductById() {
        // Arrange: Define a valid product ID
        Long productId = 1L;

        // Act: Call the service method to get a product by ID
        Product product = productService.getProductById(productId).block(); // Transform Mono<Product> into Product

        // Assert: Verify the results
        assertNotNull(product, "The product should not be null");
        assertEquals(productId, product.getId(), "The product ID should match");
        assertNotNull(product.getTitle(), "The product title should not be null");
    }
}
