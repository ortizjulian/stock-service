package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.CategoryDtoRequest;
import com.emazon.stock.application.dto.CategoryDtoResponse;
import com.emazon.stock.application.mapper.CategoryDtoRequestMapper;
import com.emazon.stock.application.mapper.PageDtoMapper;
import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryHandler implements ICategoryHandler{

    private final ICategoryServicePort categoryServicePort;
    private final PageDtoMapper pageDtoMapper;
    private final CategoryDtoRequestMapper categoryDtoRequestMapper;

    public CategoryHandler(ICategoryServicePort categoryServicePort, CategoryDtoRequestMapper categoryDtoRequestMapper, PageDtoMapper pageDtoMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryDtoRequestMapper = categoryDtoRequestMapper;
        this.pageDtoMapper = pageDtoMapper;
    }

    @Override
    public void saveCategory(CategoryDtoRequest categoryDtoRequest) {
        Category category = categoryDtoRequestMapper.toCategory(categoryDtoRequest);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public PageCustom<CategoryDtoResponse> getAllCategories(Integer page, Integer size, String sortDirection, String sortBy) {
        return pageDtoMapper.toCategoryDtoPageCustom(categoryServicePort.getAllCategories(page,size,sortDirection,sortBy));
    }

    @Override
    public void updateCategory(Long categoryId, CategoryDtoRequest categoryDtoRequest) {
        Category category = categoryDtoRequestMapper.toCategory(categoryDtoRequest);
        categoryServicePort.updateCategory(categoryId,category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryServicePort.deleteCategory(categoryId);
    }
}
