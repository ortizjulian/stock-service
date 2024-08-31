package com.emazon.stock.application.dto;

import com.emazon.stock.utils.Constants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDtoRequest {

    @NotBlank(message = Constants.EXCEPTION_CATEGORY_NAME_NULL)
    @Size(min = Constants.MIN_CHARACTERS_NAME_CATEGORY, max = Constants.MAX_CHARACTERS_NAME_CATEGORY, message = Constants.EXCEPTION_CATEGORY_NAME_SIZE)
    private String name;

    @NotBlank(message = Constants.EXCEPTION_CATEGORY_DESCRIPTION_NULL)
    @Size(min = Constants.MIN_CHARACTERS_DESCRIPTION_CATEGORY, max = Constants.MAX_CHARACTERS_DESCRIPTION_CATEGORY, message = Constants.EXCEPTION_CATEGORY_DESCRIPTION_SIZE)
    private String description;

    public CategoryDtoRequest(String name, String description) {

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
