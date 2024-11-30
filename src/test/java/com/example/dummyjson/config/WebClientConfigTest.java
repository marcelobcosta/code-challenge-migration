package com.example.dummyjson.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testng.annotations.Test;

import static org.springframework.http.HttpStatus.OK;

@SpringBootTest
public class WebClientConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testWebClientBean() {
        // Test if WebTestClient makes a successful GET request
        webTestClient.get().uri("/api/products")  // Use an actual endpoint
                .exchange()
                .expectStatus().isEqualTo(OK);  // Check if the status is 200 (OK)
    }
}
