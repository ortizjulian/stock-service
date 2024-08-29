package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Article;
import java.util.List;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    Boolean findByName(String articleName);
    List<Article> getAllArticles();
    void updateArticle(Long articleId,Article article);
    void deleteArticle(Long articleId);
}
