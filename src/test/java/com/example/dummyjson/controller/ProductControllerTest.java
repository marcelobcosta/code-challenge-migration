package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllProducts() {
        ResponseEntity<Product[]> response = restTemplate.getForEntity("/api/products", Product[].class);

        assertEquals(200, response.getStatusCodeValue(), "Expected HTTP status 200");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(2, response.getBody().length, "There should be at least two products returned");
    }

    @Test
    public void testGetProductById() {
        ResponseEntity<Product> response = restTemplate.getForEntity("/api/products/1", Product.class);

        assertEquals(200, response.getStatusCodeValue(), "Expected HTTP status 200");
        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals("Product 1", response.getBody().getTitle(), "Product title should match");
    }
}
