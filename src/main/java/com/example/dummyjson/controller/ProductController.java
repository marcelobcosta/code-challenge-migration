package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;
import reactor.core.publisher.Mono;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/products")
@Validated
public class ProductController {

    // private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    // Endpoint to fetch all products
    @GetMapping
    public Mono<List<Product>> getAllProducts() {
        return productService.getAllProducts();
                // .doOnSubscribe(subscription -> logger.info("Fetching products..."))
                // .doOnSuccess(products -> logger.info("Successfully received {} products", products.size()))
                // .doOnError(error -> logger.error("Error occurred while fetching products: ", error))
    }

    // Endpoint to fetch a product by its ID
    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable @NotNull Long id) {
        return productService.getProductById(id);
    }
}
