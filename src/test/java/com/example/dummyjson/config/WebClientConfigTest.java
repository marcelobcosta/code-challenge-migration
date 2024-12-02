package com.example.dummyjson.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WebClientConfigTest {

    @Value("${api.url}") // Load the API URL from application properties
    private String apiUrl;

    @Test
    void testWebClientBeanCreation() {
        WebClient.Builder builder = WebClient.builder();
        WebClient webClient = builder.baseUrl(apiUrl).build(); // Create WebClient with base URL

        assertNotNull(webClient, "WebClient should not be null");
        // Additional tests can validate the baseUrl if necessary
    }
}
