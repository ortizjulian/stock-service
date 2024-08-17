package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {

    void SaveCategory(Category category);
    List<Category> getAllCategories();
    void updateCategory(Category category);
    void deleteCategory(String categoryName);

}
