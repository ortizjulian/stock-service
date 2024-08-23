package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.CategoryDto;
import com.emazon.stock.domain.model.PageCustom;

public interface ICategoryHandler {
    void saveCategory(CategoryDto categoryDto);
    PageCustom<CategoryDto> getAllCategories(int page, int size, String sortDirection, String sortBy);
    void updateCategory(CategoryDto categoryDto);
    void deleteCategory(String categoryName);
}
