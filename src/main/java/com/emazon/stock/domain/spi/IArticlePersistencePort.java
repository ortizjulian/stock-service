package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.PageCustom;

public interface IArticlePersistencePort {
    void saveArticle(Article article);
    PageCustom<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName);
}
