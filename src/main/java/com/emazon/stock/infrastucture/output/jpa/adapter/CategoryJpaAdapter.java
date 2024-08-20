package com.emazon.stock.infrastucture.output.jpa.adapter;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.infrastucture.exception.CategoryAlreadyExistsException;
import com.emazon.stock.infrastucture.exception.CategoryNotFoundException;
import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import com.emazon.stock.infrastucture.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.repository.ICategoryRepository;

import java.util.List;

public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    public CategoryJpaAdapter(ICategoryRepository categoryRepository, CategoryEntityMapper categoryEntityMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
    }


    @Override
    public void saveCategory(Category category) {
        if(categoryRepository.findByName(category.getName()).isPresent()){
            throw new CategoryAlreadyExistsException();
        }

        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);

        categoryRepository.save(categoryEntity);

    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity>  categoryEntityList = categoryRepository.findAll();
        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public void updateCategory(Category category) {
        CategoryEntity categoryEntity = categoryRepository.findByName(category.getName())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with name: " + category.getName()));

        categoryEntity.setDescription(category.getDescription());
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteCategory(String categoryName) {
        categoryRepository.deleteByName(categoryName);
    }
}
