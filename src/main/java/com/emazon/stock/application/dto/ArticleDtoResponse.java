package com.emazon.stock.application.dto;

import java.util.List;

public class ArticleDtoResponse {
    private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Float price;
    private BrandDtoResponse brand;
    private List<CategoryDtoResponse> categories;

    public ArticleDtoResponse(){}


    public ArticleDtoResponse(Long id, String name, String description, Integer quantity, Float price, BrandDtoResponse brand, List<CategoryDtoResponse> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public BrandDtoResponse getBrand() {
        return brand;
    }

    public void setBrand(BrandDtoResponse brand) {
        this.brand = brand;
    }

    public List<CategoryDtoResponse> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDtoResponse> categories) {
        this.categories = categories;
    }
}
