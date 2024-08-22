package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.CategoryDto;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    void updateCategory(CategoryDto categoryDto);
    void deleteCategory(String categoryName);
}
