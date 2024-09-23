package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.PageCustom;

import java.util.List;

public interface IArticleServicePort {
    void saveArticle(Article article);
    PageCustom<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName, List<Long> articleIds);
    void updateQuantity(Long articleId, Integer quantity);
    Article getArticleById(Long articleId);
    Double getTotalPriceByArticleIds(List<Long> articleIds);
}
