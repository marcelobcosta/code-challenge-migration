package com.example.dummyjson.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class WebClientConfigTest {

    @Autowired
    private WebTestClient webTestClient;

    public void testWebClientBean() {
        // Verifique se o WebClient está inicializado e pode ser usado para fazer requisições
        webTestClient.get().uri("/some-endpoint")
                .exchange()
                .expectStatus().isOk(); // Ajuste conforme o comportamento esperado
    }
}
