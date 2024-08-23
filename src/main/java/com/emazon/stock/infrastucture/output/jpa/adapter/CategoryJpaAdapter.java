package com.emazon.stock.infrastucture.output.jpa.adapter;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.infrastucture.exception.CategoryAlreadyExistsException;
import com.emazon.stock.infrastucture.exception.CategoryNotFoundException;
import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import com.emazon.stock.infrastucture.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.mapper.PageMapper;
import com.emazon.stock.infrastucture.output.jpa.repository.ICategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final PageMapper pageMapper;

    public CategoryJpaAdapter(ICategoryRepository categoryRepository, CategoryEntityMapper categoryEntityMapper, PageMapper pageMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryEntityMapper = categoryEntityMapper;
        this.pageMapper = pageMapper;
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
    public PageCustom<Category> getAllCategories(int page, int size, String sortDirection, String sortBy) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);
        return pageMapper.toCategoryPageCustom(categoryPage);
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
