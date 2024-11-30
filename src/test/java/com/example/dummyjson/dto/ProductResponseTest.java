package com.example.dummyjson.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class ProductResponseTest {

    @Autowired
    private ProductResponse productResponse; // Autowire the ProductResponse for testing

    // Test the getter and setter for the products list
    @Test
    public void testGetSetProducts() {
        // Create some sample products
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");
        product1.setDescription("Description of Product 1");
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");
        product2.setDescription("Description of Product 2");
        product2.setPrice(20.0);

        // Create a list of products
        List<Product> productList = Arrays.asList(product1, product2);

        // Set the products in ProductResponse
        productResponse.setProducts(productList);

        // Verify that the getter returns the correct list
        Assert.assertEquals(productResponse.getProducts(), productList, "The product list should match the set value");
    }

    // Test the toString method
    @Test
    public void testToString() {
        // Create some sample products
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");
        product1.setDescription("Description of Product 1");
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");
        product2.setDescription("Description of Product 2");
        product2.setPrice(20.0);

        // Create a list of products
        List<Product> productList = Arrays.asList(product1, product2);

        // Create a ProductResponse object and set the products
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productList);

        // Verify that the toString method returns the correct string representation
        String expectedString = "ProductResponse{products=" + productList + "}";
        Assert.assertEquals(productResponse.toString(), expectedString, "The toString method should return the correct string representation");
    }
}
