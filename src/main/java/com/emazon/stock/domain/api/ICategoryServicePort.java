package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
public interface ICategoryServicePort {

    void saveCategory(Category category);
    PageCustom<Category> getAllCategories(Integer page, Integer size, String sortDirection, String sortBy);
    void updateCategory(Category category);
    void deleteCategory(String categoryName);
}
