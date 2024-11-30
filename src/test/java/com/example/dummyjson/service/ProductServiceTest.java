package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    // Test to fetch all products
    @Test
    public void testGetAllProducts() {
        // Call the API to fetch all products
        ResponseEntity<Product[]> response = restTemplate.getForEntity("/api/products", Product[].class);

        // Validate the response status is 200 OK using getStatusCode().value()
        Assert.isTrue(response.getStatusCode().value() == 200, "Expected HTTP status 200 for fetching products");

        // Ensure the response body is not null
        Assert.notNull(response.getBody(), "Response body should not be null");

        // If response body is not null, validate that at least one product is returned
        Product[] products = response.getBody();
        if (products != null) {
            Assert.isTrue(products.length > 0, "There should be at least one product returned");
        }
    }

    // Test to fetch a product by its ID
    @Test
    public void testGetProductById() {
        // Call the API to fetch the product by its ID
        ResponseEntity<Product> response = restTemplate.getForEntity("/api/products/1", Product.class);

        // Validate the response status is 200 OK using getStatusCode().value()
        Assert.isTrue(response.getStatusCode().value() == 200, "Expected HTTP status 200 for fetching a product");

        // Ensure the response body is not null
        Assert.notNull(response.getBody(), "Response body should not be null");

        // If the response body is not null, validate that the product title matches
        Product product = response.getBody();
        if (product != null) {
            Assert.isTrue("Product 1".equals(product.getTitle()), "Product title should be 'Product 1'");
        }
    }
}
