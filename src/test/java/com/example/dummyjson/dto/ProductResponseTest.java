package com.example.dummyjson.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

public class ProductResponseTest {

    @Test
    public void testGetSetProducts() {
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

        List<Product> productList = Arrays.asList(product1, product2);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productList);

        assertEquals(productList, productResponse.getProducts(), "The product list should match the set value");
    }

    @Test
    public void testToString() {
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

        List<Product> productList = Arrays.asList(product1, product2);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProducts(productList);

        String expectedString = "ProductResponse{products=" + productList + "}";
        assertEquals(expectedString, productResponse.toString(), "The toString method should return the correct string representation");
    }
}
