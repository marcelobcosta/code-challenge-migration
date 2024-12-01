package com.example.dummyjson.dto;

import java.util.List;

public class ProductResponse {
    private List<Product> products;

    // Getter and Setter for products list
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // String representation of the ProductResponse object
    @Override
    public String toString() {
        return "ProductResponse{" +
                "products=" + products +
                '}';
    }
}
