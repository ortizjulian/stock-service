package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.ArticleDtoRequest;
import com.emazon.stock.application.dto.ArticleDtoResponse;
import com.emazon.stock.application.dto.PriceDto;
import com.emazon.stock.application.dto.UpdateQuantityRequest;
import com.emazon.stock.domain.model.PageCustom;

import java.util.List;

public interface IArticleHandler {
        void saveArticle(ArticleDtoRequest articleDtoRequest);
        PageCustom<ArticleDtoResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName, List<Long> articleIds);
        void updateQuantity(UpdateQuantityRequest updateQuantityRequest);
        ArticleDtoResponse getArticleById(Long articleId);
        PriceDto getTotalPriceByArticleIds(List<Long> articleIds);
}
