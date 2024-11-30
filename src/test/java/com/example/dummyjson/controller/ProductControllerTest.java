package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private WebClient webClient;  // Inject WebClient

    // Test to fetch all products
    @Test
    public void testGetAllProducts() {
        // Send GET request and get response
        Product[] products = webClient.get()
                .uri("/api/products")
                .retrieve()
                .bodyToMono(Product[].class)
                .block();  // Block to make it synchronous in test

        // Check if response is not null
        assertNotNull(products, "Response body should not be null");

        // Check the expected number of products
        if (products != null) {
            assertEquals(2, products.length, "There should be at least two products returned");
        }
    }

    // Test to fetch a product by its ID
    @Test
    public void testGetProductById() {
        // Send GET request and get product response
        Product product = webClient.get()
                .uri("/api/products/1")
                .retrieve()
                .bodyToMono(Product.class)
                .block();  // Block to make it synchronous in test

        // Check if product is not null
        assertNotNull(product, "Response body should not be null");

        // Verify product title
        if (product != null) {
            assertEquals("Product 1", product.getTitle(), "Product title should match");
        }
    }
}
