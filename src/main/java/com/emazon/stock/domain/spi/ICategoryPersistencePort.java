package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;

public interface ICategoryPersistencePort {

    void saveCategory(Category category);
    Boolean existsByName(String categoryName);
    Boolean existById(Long categoryId);
    PageCustom<Category> getAllCategories(Integer page, Integer size, String sortDirection, String sortBy);
    void updateCategory(Long categoryId,Category category);
    void deleteCategory(Long categoryId);

}
