package com.example.dummyjson.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class WebClientConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    public void testWebClientBean() {
        webTestClient.get().uri("/some-endpoint")
                .exchange()
                .expectStatus().isOk(); 
    }
}
