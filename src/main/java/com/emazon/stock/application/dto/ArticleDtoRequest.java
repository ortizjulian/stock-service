package com.emazon.stock.application.dto;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.utils.Constants;
import jakarta.validation.constraints.*;

import java.util.Set;

public class ArticleDtoRequest {

    @NotBlank(message = Constants.EXCEPTION_ARTICLE_NAME_BLANK)
    private String name;

    @NotBlank(message = Constants.EXCEPTION_ARTICLE_DESCRIPTION_BLANK)
    private String description;

    @Min(value = Constants.MIN_ARTICLE_QUANTITY, message = Constants.EXCEPTION_ARTICLE_QUANTITY_NEGATIVE)
    private Integer quantity;

    @DecimalMin(value = Constants.MIN_ARTICLE_PRICE, inclusive = false, message = Constants.EXCEPTION_ARTICLE_PRICE_NEGATIVE)
    private Float price;

    @NotNull(message = Constants.EXCEPTION_ARTICLE_BRAND_NULL)
    private Brand brand;

    @NotNull(message = Constants.EXCEPTION_ARTICLE_CATEGORIES_NULL)
    @Size(min = Constants.MIN_ARTICLE_CATEGORIES,max =  Constants.MAX_ARTICLE_CATEGORIES, message = Constants.EXCEPTION_ARTICLE_CATEGORIES_SIZE)
    private Set<Category> categories;

    public ArticleDtoRequest(String name, String description, Integer quantity, Float price, Brand brand, Set<Category> categories) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

}

