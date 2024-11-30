package com.example.dummyjson.service.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    private static final Logger logger = LoggerFactory.getLogger(CustomHealthIndicator.class);

    private final WebClient webClient;

    @Value("${api.url}")
    private String apiUrl;

    public CustomHealthIndicator(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }

    @Override
    public Health health() {
        long startTime = System.currentTimeMillis();

        logger.info("Checking API health with URL: {}", apiUrl);

        Mono<String> responseMono = webClient.get()
                .uri(apiUrl + "/products/1")
                .retrieve()
                .bodyToMono(String.class)
                .doOnTerminate(() -> {
                    long responseTime = System.currentTimeMillis() - startTime;
                    logger.info("Response time: {} ms", responseTime);
                });

        return responseMono.map(response -> {
                    logger.info("API Response: {}", response);
                    return Health.up()
                            .withDetail("apiStatus", "Available")
                            .withDetail("responseBody", response)
                            .withDetail("responseTime", System.currentTimeMillis() - startTime)
                            .build();
                })
                .onErrorResume(error -> {
                    logger.error("API Error: {}", error.getMessage());
                    return Mono.just(Health.down()
                            .withDetail("apiStatus", "Unavailable")
                            .withDetail("error", error.getMessage())
                            .build());
                })
                .block();  // Bloqueia para garantir que a resposta da API seja processada antes de retornar
    }
}
