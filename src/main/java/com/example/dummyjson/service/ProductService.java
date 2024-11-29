package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ProductService {

    private final String BASE_URL = "https://dummyjson.com/products";

    @Autowired
    private WebClient webClient;

    public List<Product> getAllProducts() {
        // Using WebClient to fetch all products
        return webClient.get()
                .uri(BASE_URL)
                .retrieve()
                .bodyToFlux(Product.class) // Converts response to a Flux<Product>
                .collectList()             // Collects Flux<Product> into a List<Product>
                .block();                  // Blocks the reactive chain and returns the result
    }

    public Product getProductById(Long id) {
        String url = BASE_URL + "/" + id;
        // Using WebClient to fetch a product by ID
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Product.class) // Converts response to a Mono<Product>
                .block();                  // Blocks and retrieves the Product
    }
}
