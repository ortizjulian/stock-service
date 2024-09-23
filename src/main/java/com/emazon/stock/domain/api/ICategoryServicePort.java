package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;

import java.util.List;
import java.util.Map;

public interface ICategoryServicePort {

    void saveCategory(Category category);
    PageCustom<Category> getAllCategories(Integer page, Integer size, String sortDirection, String sortBy);
    void updateCategory(Long categoryId,Category category);
    void deleteCategory(Long categoryId);
    Map<String, Long> getCategoryQuantities(List<Long> articlesIds);
}
