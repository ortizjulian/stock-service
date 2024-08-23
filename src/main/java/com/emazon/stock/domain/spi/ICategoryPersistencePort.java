package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);
    PageCustom<Category> getAllCategories(int page, int size, String sortDirection, String sortBy);
    void updateCategory(Category category);
    void deleteCategory(String categoryName);

}
