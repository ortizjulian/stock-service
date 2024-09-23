package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.api.ICategoryServicePort;
import com.emazon.stock.domain.exception.CategoryAlreadyExistsException;
import com.emazon.stock.domain.exception.CategoryNotFoundException;
import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.IArticlePersistencePort;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.domain.utils.PaginationValidator;
import com.emazon.stock.utils.Constants;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CategoryUseCase implements ICategoryServicePort {

    private ICategoryPersistencePort categoryPersistencePort;
    private IArticlePersistencePort articlePersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort, IArticlePersistencePort articlePersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
        this.articlePersistencePort = articlePersistencePort;
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

    @Override
    public Map<String, Long> getCategoryQuantities(List<Long> articlesIds) {

        List<Article> articles = articlePersistencePort.getArticlesByIds(articlesIds);

        return articles.stream()
                .flatMap(article -> article.getCategories().stream())
                .collect(Collectors.groupingBy(
                        Category::getName,
                        Collectors.counting()
                ));
    }
}
