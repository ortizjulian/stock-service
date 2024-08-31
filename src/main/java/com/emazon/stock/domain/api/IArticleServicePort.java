package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.PageCustom;

public interface IArticleServicePort {
    void saveArticle(Article article);
    PageCustom<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName);
}
