package com.example.dummyjson.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HealthControllerTest {

    @Autowired
    private HealthController healthController;

    @Test
    void testHealthCheckSuccess() {
        Mono<ResponseEntity<Map<String, Object>>> responseMono = healthController.getHealthStatus();
        ResponseEntity<Map<String, Object>> response = responseMono.block(); // Blocking for testing purposes

        assertNotNull(response, "Response should not be null");
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Status code should be 200 OK");
        Map<String, Object> body = response.getBody();
        assertNotNull(body, "Response body should not be null");

        assertEquals("UP", body.get("status"), "Application status should be UP");
        assertNotNull(body.get("externalApi"), "External API status should be present");
        assertNotNull(body.get("uptime"), "Uptime should be present");
        assertNotNull(body.get("memory"), "Memory usage should be present");
        assertNotNull(body.get("version"), "Application version should be present");
        assertNotNull(body.get("system"), "System info should be present");
        assertNotNull(body.get("timestamp"), "Timestamp should be present");
    }

    @Test
    void testHealthCheckFailure() {
        // Temporarily override WebClient behavior for this test
        WebClient mockWebClient = WebClient.builder().baseUrl("http://invalid-url").build();
        HealthController healthController = new HealthController(mockWebClient);

        Mono<ResponseEntity<Map<String, Object>>> responseMono = healthController.getHealthStatus();
        ResponseEntity<Map<String, Object>> response = responseMono.block(); // Blocking for testing purposes

        assertNotNull(response, "Response should not be null");
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode(),
                "Status code should be 503 SERVICE_UNAVAILABLE");
        Map<String, Object> body = response.getBody();
        assertNotNull(body, "Response body should not be null");

        assertEquals("DOWN", body.get("status"), "Application status should be DOWN");
        assertNotNull(body.get("externalApi"), "External API status should be present");
        assertEquals("DOWN", ((Map<?, ?>) body.get("externalApi")).get("status"), "External API status should be DOWN");
    }
}
