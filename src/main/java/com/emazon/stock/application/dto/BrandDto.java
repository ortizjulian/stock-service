package com.emazon.stock.application.dto;

import com.emazon.stock.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class BrandDto {

    @NotNull(message = Constants.EXCEPTION_BRAND_NAME_NULL)
    @Size(min = Constants.MIN_CHARACTERS_NAME_BRAND, max = Constants.MAX_CHARACTERS_NAME_BRAND, message = Constants.EXCEPTION_BRAND_NAME_SIZE)
    private String name;

    @NotNull(message = Constants.EXCEPTION_BRAND_DESCRIPTION_NULL)
    @Size(min = Constants.MIN_CHARACTERS_DESCRIPTION_BRAND, max = Constants.MAX_CHARACTERS_DESCRIPTION_BRAND, message = Constants.EXCEPTION_BRAND_DESCRIPTION_SIZE)
    private String description;

    public BrandDto(String name, String description) {

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
