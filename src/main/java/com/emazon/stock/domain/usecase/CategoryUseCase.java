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

    private ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    
    @Override
    public void saveCategory(Category category) {

        if(categoryPersistencePort.existsByName(category.getName())){
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
    public void updateCategory(Long categoryId,Category category) {
        if(!categoryPersistencePort.existById(categoryId)){
            throw new CategoryNotFoundException(Constants.EXCEPTION_CATEGORY_NOT_FOUND_BY_ID + categoryId);
        }
        this.categoryPersistencePort.updateCategory(categoryId,category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        if(!categoryPersistencePort.existById(categoryId)){
            throw new CategoryNotFoundException(Constants.EXCEPTION_CATEGORY_NOT_FOUND_BY_ID + categoryId);
        }
        this.categoryPersistencePort.deleteCategory(categoryId);
    }
}
