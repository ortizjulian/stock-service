package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.CategoryDto;
import com.emazon.stock.application.mapper.CategoryDtoMapper;
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
    private final CategoryDtoMapper categoryDtoMapper;
    private final PageDtoMapper pageDtoMapper;

    public CategoryHandler(ICategoryServicePort categoryServicePort, CategoryDtoMapper categoryDtoMapper, PageDtoMapper pageDtoMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryDtoMapper = categoryDtoMapper;
        this.pageDtoMapper = pageDtoMapper;
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category category = categoryDtoMapper.toCategory(categoryDto);
        categoryServicePort.saveCategory(category);
    }

    @Override
    public PageCustom<CategoryDto> getAllCategories(int page, int size, String sortDirection, String sortBy) {
        return pageDtoMapper.toCategoryDtoPageCustom(categoryServicePort.getAllCategories(page,size,sortDirection,sortBy));
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        Category category = categoryDtoMapper.toCategory(categoryDto);
        categoryServicePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(String categoryName) {
        categoryServicePort.deleteCategory(categoryName);
    }
}
