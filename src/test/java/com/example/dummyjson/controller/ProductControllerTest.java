package com.example.dummyjson.controller;

import com.example.dummyjson.dto.Product;
import com.example.dummyjson.service.ProductService;

import reactor.core.publisher.Mono;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class) // Usando a extensão do Mockito com JUnit 5
public class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setTitle("Product 2");

        List<Product> products = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(Mono.just(products)); // Retornando um Mono<List<Product>>

        List<Product> result = productController.getAllProducts().block(); // Usando .block() para esperar o Mono retornar a lista
        assertEquals(2, result.size()); // Verificando o tamanho da lista
        assertEquals("Product 1", result.get(0).getTitle()); // Verificando o título do primeiro produto
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product 1");

        when(productService.getProductById(1L)).thenReturn(Mono.just(product)); // Retornando um Mono<Product>

        Product result = productController.getProductById(1L).block(); // Usando .block() para esperar o Mono retornar o produto
        assertEquals("Product 1", result.getTitle()); // Verificando o título do produto
    }
}
