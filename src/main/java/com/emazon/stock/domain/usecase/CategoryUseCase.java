package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    
    @Override
    public void SaveCategory(Category category) {
        this.categoryPersistencePort.SaveCategory(category);
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
