package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.exception.DataConstraintViolationException;
import com.emazon.stock.domain.exception.MissingAttributeException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    
    @Override
    public void saveCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new MissingAttributeException("Category name cannot be empty");
        }
        if (category.getDescription() == null || category.getDescription().isEmpty()) {
            throw new MissingAttributeException("Category description cannot be empty");
        }
        if(category.getName().length() > 50) {
            throw new DataConstraintViolationException("Category name exceeds the maximum length of 50 characters");
        }
        if(category.getDescription().length() > 90) {
            throw new DataConstraintViolationException("Category description exceeds the maximum length of 90 characters");
        }

        this.categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryPersistencePort.getAllCategories();
    }

    @Override
    public void updateCategory(Category category) {
        this.categoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(String categoryName) {
        this.categoryPersistencePort.deleteCategory(categoryName);
    }
}
