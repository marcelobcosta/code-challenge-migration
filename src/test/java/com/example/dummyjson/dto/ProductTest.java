package com.example.dummyjson.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTest {

    @Autowired
    private TestRestTemplate restTemplate;

    public void testGetAndSetter() {
        // Criando um produto para testar
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("A dummy title");
        expectedProduct.setDescription("A dummy description");
        expectedProduct.setPrice(2.1);

        // Usando TestRestTemplate para simular a requisição para o endpoint
        ResponseEntity<Product> response = restTemplate.getForEntity("/api/products/1", Product.class);

        // Verificando se o produto retornado é o esperado
        Assert.isTrue(response.getStatusCodeValue() == 200, "Expected HTTP status 200");
        Assert.notNull(response.getBody(), "Response body should not be null");

        Product actualProduct = response.getBody();
        Assert.notNull(actualProduct, "Product should not be null");
        Assert.isTrue(expectedProduct.getId().equals(actualProduct.getId()), "ID should match");
        Assert.isTrue(expectedProduct.getTitle().equals(actualProduct.getTitle()), "Title should match");
        Assert.isTrue(expectedProduct.getDescription().equals(actualProduct.getDescription()), "Description should match");
        Assert.isTrue(expectedProduct.getPrice().equals(actualProduct.getPrice()), "Price should match");
    }
}
