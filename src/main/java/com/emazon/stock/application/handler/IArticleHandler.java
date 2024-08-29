package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.ArticleDtoRequest;
import com.emazon.stock.application.dto.ArticleDtoResponse;
import com.emazon.stock.domain.model.Article;

import java.util.List;

public interface IArticleHandler {

        void saveArticle(ArticleDtoRequest articleDtoRequest);
        List<ArticleDtoResponse> getAllArticles();
        void updateArticle(Long articleId,ArticleDtoRequest articleDtoRequest);
        void deleteArticle(Long articleId);
}
