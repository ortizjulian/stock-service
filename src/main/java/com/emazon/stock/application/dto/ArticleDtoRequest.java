package com.emazon.stock.application.dto;

import com.emazon.stock.utils.Constants;
import jakarta.validation.constraints.*;

import java.util.List;

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
    private Long brandId;

    @NotNull(message = Constants.EXCEPTION_ARTICLE_CATEGORIES_NULL)
    @Size(min = Constants.MIN_ARTICLE_CATEGORIES, max = Constants.MAX_ARTICLE_CATEGORIES, message = Constants.EXCEPTION_ARTICLE_CATEGORIES_SIZE)
    private List<Long> categoryIds;

    public ArticleDtoRequest(String name, String description, Integer quantity, Float price, Long brandId, List<Long> categoryIds) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.brandId = brandId;
        this.categoryIds = categoryIds;
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

    public  Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }
}

