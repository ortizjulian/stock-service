package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.CategoryDto;
import com.emazon.stock.domain.model.PageCustom;

public interface ICategoryHandler {
    void saveCategory(CategoryDto categoryDto);
    PageCustom<CategoryDto> getAllCategories(Integer page, Integer size, String sortDirection, String sortBy);
    void updateCategory(CategoryDto categoryDto);
    void deleteCategory(String categoryName);
}
