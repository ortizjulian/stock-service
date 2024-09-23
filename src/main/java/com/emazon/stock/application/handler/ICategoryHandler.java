package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.CategoryDtoRequest;
import com.emazon.stock.application.dto.CategoryDtoResponse;
import com.emazon.stock.application.dto.CategoryQuantityResponse;
import com.emazon.stock.domain.model.PageCustom;

import java.util.List;

public interface ICategoryHandler {
    void saveCategory(CategoryDtoRequest categoryDtoRequest);
    PageCustom<CategoryDtoResponse> getAllCategories(Integer page, Integer size, String sortDirection, String sortBy);
    void updateCategory(Long categoryId,CategoryDtoRequest categoryDto);
    void deleteCategory(Long categoryId);
    List<CategoryQuantityResponse> getCategoryQuantities(List<Long> articlesIds);
}
