package com.example.dummyjson.service;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.dto.ProductResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;

// Service class to handle product data
@Service
public class ProductService {

    // Base URL for the API from application properties
    @Value("${api.url}")
    private String baseUrl;

    @Autowired
    private WebClient webClient;

    // Logger to log information (optional)
    // private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    // Fetch all products from the API
    public Mono<List<Product>> getAllProducts() {
        String url = baseUrl + "/products"; // Construct API URL
        return webClient.get()
                .uri(url) 
                .retrieve() // Perform the GET request
                .bodyToMono(ProductResponse.class) // Deserialize response into ProductResponse
                .map(ProductResponse::getProducts) // Extract list of products
                // .doOnSuccess(products -> logger.info("Successfully received products: {}", products))
                // .doOnError(error -> logger.error("Error fetching products", error))
                ;
    }

    // Fetch a product by its ID from the API
    public Mono<Product> getProductById(Long id) {
        // logger.info("Fetching all products from the API...");
        String url = baseUrl + "/products/" + id; // Construct URL for specific product
        return webClient.get()
                .uri(url)
                .retrieve() // Perform the GET request
                .bodyToMono(Product.class) // Deserialize response into Product
                // .doOnSuccess(products -> logger.info("Received products: {}", products))
                // .doOnError(error -> logger.error("Error fetching products", error))
                ;
    }
}
