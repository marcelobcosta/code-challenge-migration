package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    public void testGetAllProducts() {
        ResponseEntity<Product[]> response = restTemplate.getForEntity("/api/products", Product[].class);
        
        Assert.isTrue(response.getStatusCodeValue() == 200, "Expected HTTP status 200");
        
        Assert.notNull(response.getBody(), "Response body should not be null");
        
        Assert.isTrue(response.getBody().length > 0, "There should be at least one product returned");
    }

    public void testGetProductById() {
        ResponseEntity<Product> response = restTemplate.getForEntity("/api/products/1", Product.class);
        
        Assert.isTrue(response.getStatusCodeValue() == 200, "Expected HTTP status 200");
        
        Assert.notNull(response.getBody(), "Response body should not be null");
        
        Assert.isTrue("Product 1".equals(response.getBody().getTitle()), "Product title should match");
    }
}
