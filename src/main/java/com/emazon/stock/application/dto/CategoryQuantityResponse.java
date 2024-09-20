package com.emazon.stock.application.dto;

public class CategoryQuantityResponse {
    private String categoryName;
    private Long quantity;

    public CategoryQuantityResponse(String categoryName, Long quantity) {
        this.categoryName = categoryName;
        this.quantity = quantity;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
