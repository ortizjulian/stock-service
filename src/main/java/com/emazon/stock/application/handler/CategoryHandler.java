package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.CategoryDto;
import com.emazon.stock.application.mapper.CategoryDtoMapper;
import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryHandler implements ICategoryHandler{

    private final ICategoryServicePort categoryServicePort;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryHandler(ICategoryServicePort categoryServicePort, CategoryDtoMapper categoryDtoMapper) {
        this.categoryServicePort = categoryServicePort;
        this.categoryDtoMapper = categoryDtoMapper;
    }


    @Override
    public void SaveCategory(CategoryDto categoryDto) {
        Category category = categoryDtoMapper.toCategory(categoryDto);
        categoryServicePort.SaveCategory(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryDtoMapper.toDtoList(categoryServicePort.getAllCategories());
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
