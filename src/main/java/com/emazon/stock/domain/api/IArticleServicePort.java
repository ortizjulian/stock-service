package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Article;

import java.util.List;

public interface IArticleServicePort {
    void saveArticle(Article article);
    List<Article> getAllArticles();
    void updateArticle(Long articleId,Article article);
    void deleteArticle(Long articleId);
}
