package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.ArticleDtoRequest;
import com.emazon.stock.application.dto.ArticleDtoResponse;

import java.util.List;

public interface IArticleHandler {

        void saveArticle(ArticleDtoRequest articleDtoRequest);
        List<ArticleDtoResponse> getAllArticles();
}
