package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.api.IArticleServicePort;
import com.emazon.stock.domain.exception.BrandNotFoundException;
import com.emazon.stock.domain.exception.CategoryNotFoundException;
import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.IArticlePersistencePort;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import com.emazon.stock.utils.Constants;

import java.util.List;
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
            throw new BrandNotFoundException(Constants.EXCEPTION_BRAND_NOT_FOUND +article.getBrand().getId());
        }

        Set<Long> invalidCategoryIds = article.getCategories().stream()
                .map(Category::getId)
                .filter(categoryId -> !categoryPersistencePort.existById(categoryId))
                .collect(Collectors.toSet());

        if (!invalidCategoryIds.isEmpty()) {
                    throw new CategoryNotFoundException(Constants.EXCEPTION_CATEGORY_NOT_FOUND + invalidCategoryIds);
        }

        this.articlePersistencePort.saveArticle(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return this.articlePersistencePort.getAllArticles();
    }
}
