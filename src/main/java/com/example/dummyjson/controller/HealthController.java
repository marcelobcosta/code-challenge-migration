package com.example.dummyjson.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.management.ManagementFactory;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthController {

    private final WebClient webClient;

    public HealthController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/health")
    public Mono<ResponseEntity<Map<String, Object>>> getHealthStatus() {
        return webClient.get()
                .uri("/products") // Check a lightweight endpoint
                .retrieve()
                .toBodilessEntity()
                .map(response -> {
                    if (response.getStatusCode().is2xxSuccessful()) {
                        return buildDetailedResponse("UP", "UP", null, HttpStatus.OK);
                    } else {
                        return buildDetailedResponse("DOWN", "DOWN", "External API returned non-2xx status", HttpStatus.SERVICE_UNAVAILABLE);
                    }
                })
                .onErrorResume(error -> Mono.just(buildDetailedResponse("DOWN", "DOWN", error.getMessage(), HttpStatus.SERVICE_UNAVAILABLE)));
    }

    private ResponseEntity<Map<String, Object>> buildDetailedResponse(String appStatus, String apiStatus, String error, HttpStatus httpStatus) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", appStatus);

        Map<String, Object> externalApi = new HashMap<>();
        externalApi.put("status", apiStatus);
        if (error != null) {
            externalApi.put("error", error);
        }
        response.put("externalApi", externalApi);
        response.put("uptime", getUptime());
        response.put("memory", getMemoryUsage());
        response.put("version", getAppVersion());
        response.put("system", getSystemInfo());
        response.put("timestamp", Instant.now().toString());

        return new ResponseEntity<>(response, httpStatus);
    }

    private String getUptime() {
        long uptimeMillis = ManagementFactory.getRuntimeMXBean().getUptime();
        return Duration.ofMillis(uptimeMillis).toString();
    }

    private Map<String, Object> getMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        return Map.of(
            "totalMemory", totalMemory,
            "freeMemory", freeMemory,
            "usedMemory", usedMemory
        );
    }

    private String getAppVersion() {
        return "1.0.0"; // Replace with actual version retrieval logic if needed
    }

    private Map<String, String> getSystemInfo() {
        return Map.of(
            "os", System.getProperty("os.name"),
            "architecture", System.getProperty("os.arch"),
            "javaVersion", System.getProperty("java.version")
        );
    }
}
