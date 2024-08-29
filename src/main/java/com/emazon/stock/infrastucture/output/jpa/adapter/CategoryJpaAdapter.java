package com.emazon.stock.infrastucture.output.jpa.adapter;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import com.emazon.stock.infrastucture.output.jpa.mapper.CategoryEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.mapper.PageMapper;
import com.emazon.stock.infrastucture.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final PageMapper pageMapper;

    @Override
    public void saveCategory(Category category) {
        CategoryEntity categoryEntity = categoryEntityMapper.toEntity(category);

        categoryRepository.save(categoryEntity);

    }

    @Override
    public Boolean findByName(String categoryName) {
        return categoryRepository.findByName(categoryName).isPresent();
    }

    @Override
    public Boolean findById(Long categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }

    @Override
    public PageCustom<Category> getAllCategories(Integer page, Integer size, String sortDirection, String sortBy) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CategoryEntity> categoryPage = categoryRepository.findAll(pageable);
        return pageMapper.toCategoryPageCustom(categoryPage);
    }


    @Override
    public void updateCategory(Long categoryId,Category category) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(categoryId);

        if (optionalCategoryEntity.isPresent()) {
            CategoryEntity categoryEntity = optionalCategoryEntity.get();
            categoryEntity.setName(category.getName());
            categoryEntity.setDescription(category.getDescription());
            categoryRepository.save(categoryEntity);
        }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
