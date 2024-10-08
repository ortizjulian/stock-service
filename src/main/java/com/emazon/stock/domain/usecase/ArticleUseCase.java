package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.api.IArticleServicePort;
import com.emazon.stock.domain.exception.BrandNotFoundException;
import com.emazon.stock.domain.exception.CategoryNotFoundException;
import com.emazon.stock.domain.exception.DuplicateCategoryException;
import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.IArticlePersistencePort;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.domain.utils.PaginationValidator;
import com.emazon.stock.utils.Constants;
import java.util.Set;
import java.util.stream.Collectors;

public class ArticleUseCase implements IArticleServicePort {

    private final IArticlePersistencePort articlePersistencePort;
    private final IBrandPersistencePort brandPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;

    public ArticleUseCase(IArticlePersistencePort articlePersistencePort, IBrandPersistencePort brandPersistencePort, ICategoryPersistencePort categoryPersistencePort) {
        this.articlePersistencePort = articlePersistencePort;
        this.brandPersistencePort = brandPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void saveArticle(Article article) {

        if (!brandPersistencePort.existById(article.getBrand().getId())) {
            throw new BrandNotFoundException(Constants.EXCEPTION_BRAND_NOT_FOUND_BY_ID +article.getBrand().getId());
        }

        Set<Long> invalidCategoryIds = article.getCategories().stream()
                .map(Category::getId)
                .filter(categoryId -> !categoryPersistencePort.existById(categoryId))
                .collect(Collectors.toSet());

        Set<Long> categoryIds = article.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toSet());

        if (categoryIds.size() != article.getCategories().size()) {
            throw new DuplicateCategoryException();
        }

        if (!invalidCategoryIds.isEmpty()) {
                    throw new CategoryNotFoundException(Constants.EXCEPTION_CATEGORY_NOT_FOUND_BY_ID + invalidCategoryIds);
        }

        this.articlePersistencePort.saveArticle(article);
    }

    @Override
    public PageCustom<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName) {

        PaginationValidator.validatePagination(page,size,sortDirection);

        if (brandName != null && !brandName.isBlank() && !brandPersistencePort.existsByName(brandName)) {
                throw new BrandNotFoundException(Constants.EXCEPTION_BRAND_NOT_FOUND_BY_NAME + brandName);

        }

        if (categoryName != null && !categoryName.isBlank() && !categoryPersistencePort.existsByName(categoryName)) {
            throw new CategoryNotFoundException(Constants.EXCEPTION_CATEGORY_NOT_FOUND_BY_NAME + categoryName);
        }
        return this.articlePersistencePort.getAllArticles(page,size,sortDirection,sortBy,brandName,categoryName);
    }
}
