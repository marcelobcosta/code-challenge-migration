package com.example.dummyjson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // Bean for WebClient creation
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();  // Build and return WebClient instance
    }
}
