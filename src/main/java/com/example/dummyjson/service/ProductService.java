package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// Service class to handle product data
@Service
public class ProductService {

    private final WebClient webClient;

    // Logger to log information (optional)
    // private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    // Constructor injection for better testability and immutability
    @Autowired
    public ProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    // Fetch all products from the API
    public Mono<List<Product>> getAllProducts() {
        return webClient.get()
                .uri("/products") // Relative URI
                .retrieve() // Perform the GET request
                .bodyToMono(ProductResponse.class) // Deserialize response into ProductResponse
                .map(ProductResponse::getProducts) // Extract list of products
                // .doOnSuccess(products -> logger.info("Successfully received products: {}", products))
                // .doOnError(error -> logger.error("Error fetching products", error))
                ;
    }

    // Fetch a product by its ID from the API
    public Mono<Product> getProductById(Long id) {
        return webClient.get()
                .uri("/products/{id}", id) // Relative URI with path variable
                .retrieve() // Perform the GET request
                .bodyToMono(Product.class) // Deserialize response into Product
                // .doOnSuccess(product -> logger.info("Successfully received product: {}", product))
                // .doOnError(error -> logger.error("Error fetching product by ID", error))
                ;
    }
}
