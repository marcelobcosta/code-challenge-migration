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

@Service
public class ProductService {

    private final String BASE_URL = "https://dummyjson.com/products";

    @Autowired
    private WebClient webClient;

    // private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public Mono<List<Product>> getAllProducts() {
        return webClient.get()
                .uri(BASE_URL)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .map(ProductResponse::getProducts)
                // .doOnSuccess(products -> logger.info("Successfully received products: {}", products))
                // .doOnError(error -> logger.error("Error fetching products", error));
    }

    public Mono<Product> getProductById(Long id) {
        // logger.info("Fetching all products from the API...");
        String url = BASE_URL + "/" + id;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Product.class)
                // .doOnSuccess(products -> logger.info("Received products: {}", products))
                // .doOnError(error -> logger.error("Error fetching products", error));
    }
}
