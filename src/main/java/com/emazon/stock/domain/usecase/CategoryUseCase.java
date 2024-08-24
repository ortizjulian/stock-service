package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.exception.CategoryAlreadyExistsException;
import com.emazon.stock.domain.exception.CategoryNotFoundException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.domain.utils.PaginationValidator;
import com.emazon.stock.utils.Constants;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    
    @Override
    public void saveCategory(Category category) {

        if(categoryPersistencePort.findByName(category.getName())){
            throw new CategoryAlreadyExistsException();
        }

        this.categoryPersistencePort.saveCategory(category);
    }

    @Override
    public PageCustom<Category> getAllCategories(Integer page, Integer size, String sortDirection, String sortBy) {
        PaginationValidator.validatePagination(page,size,sortDirection);
        return this.categoryPersistencePort.getAllCategories(page,size,sortDirection,sortBy);
    }

    @Override
    public void updateCategory(Category category) {
        if(!categoryPersistencePort.findByName(category.getName())){
            throw new CategoryNotFoundException(Constants.EXCEPTION_CATEGORY_NOT_FOUND + category.getName());
        }
        this.categoryPersistencePort.updateCategory(category);
    }

    @Override
    public void deleteCategory(String categoryName) {
        if(!categoryPersistencePort.findByName(categoryName)){
            throw new CategoryNotFoundException(Constants.EXCEPTION_CATEGORY_NOT_FOUND + categoryName);
        }
        this.categoryPersistencePort.deleteCategory(categoryName);
    }
}
