package com.example.dummyjson.dto;

import jakarta.validation.constraints.NotNull;

public class Product {

    @NotNull
    private Long id;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private Double price;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", title='" + title + "', price=" + price +  "'}";
    }
}
