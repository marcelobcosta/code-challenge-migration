package com.example.dummyjson.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTest {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    public void testGetAndSetters() {
        // Setup expected Product object
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("A dummy title");
        expectedProduct.setDescription("A dummy description");
        expectedProduct.setPrice(2.1);

        // Create WebClient instance
        WebClient webClient = webClientBuilder.baseUrl("http://localhost:8080").build();

        // Call the API to get the product by ID
        Mono<Product> productMono = webClient.get()
                .uri("/api/products/1")
                .retrieve()
                .bodyToMono(Product.class)
                .onErrorMap(WebClientResponseException.class, e -> {
                    if (e.getStatusCode().is4xxClientError() || e.getStatusCode().is5xxServerError()) {
                        return new WebClientResponseException(
                                "Error during request", 
                                e.getStatusCode().value(),
                                e.getStatusText(),
                                e.getHeaders(),
                                null,
                                null);
                    }
                    return e; // propagate other errors as is
                });

        // Retrieve and validate the response
        Product actualProduct = productMono.block(); // block to make it synchronous in this test case

        // Assert that the response body is not null
        Assert.assertNotNull(actualProduct, "Response body should not be null");

        // Assert that all properties match
        Assert.assertEquals(expectedProduct.getId(), actualProduct.getId(), "ID should match");
        Assert.assertEquals(expectedProduct.getTitle(), actualProduct.getTitle(), "Title should match");
        Assert.assertEquals(expectedProduct.getDescription(), actualProduct.getDescription(), "Description should match");
        Assert.assertEquals(expectedProduct.getPrice(), actualProduct.getPrice(), "Price should match");
    }
}
