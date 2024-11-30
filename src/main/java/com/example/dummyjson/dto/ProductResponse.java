package com.example.dummyjson.dto;

import java.util.List;

public class ProductResponse {
    private List<Product> products;

    // Getter e Setter
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "products=" + products +
                '}';
    }
}