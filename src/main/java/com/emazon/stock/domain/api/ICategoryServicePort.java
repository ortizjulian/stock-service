package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
public interface ICategoryServicePort {

    void saveCategory(Category category);
    PageCustom<Category> getAllCategories(int page, int size, String sortDirection, String sortBy);
    void updateCategory(Category category);
    void deleteCategory(String categoryName);
}
