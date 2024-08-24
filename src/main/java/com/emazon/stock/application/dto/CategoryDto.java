package com.emazon.stock.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CategoryDto {

    @NotNull(message = "The name must not be null")
    @Size(min = 1, max = 50, message = "The name must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "The description must not be null")
    @Size(max = 90, message = "The description must not exceed 90 characters")
    private String description;

    public CategoryDto(String name, String description) {

        this.name = name;
        this.description = description;
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

}
