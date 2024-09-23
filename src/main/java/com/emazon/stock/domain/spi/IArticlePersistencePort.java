package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.PageCustom;

import java.util.List;
import java.util.Optional;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    Boolean existById(Long articleId);
    PageCustom<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName, List<Long> articleIds);
    void updateQuantity(Long articleId, Integer quantity);
    Optional<Article> getArticleById(Long articleId);
    List<Article> getArticlesByIds(List<Long> articlesIds);
}
