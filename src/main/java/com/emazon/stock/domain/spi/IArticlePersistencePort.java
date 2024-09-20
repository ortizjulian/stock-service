package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.PageCustom;

import java.util.List;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    Boolean existById(Long articleId);
    PageCustom<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName);
    void updateQuantity(Long articleId, Integer quantity);
    Article getArticleById(Long articleId);
    List<Article> getArticlesByIds(List<Integer> articlesIds);
}
