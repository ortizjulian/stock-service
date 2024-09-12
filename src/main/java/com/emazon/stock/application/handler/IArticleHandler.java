package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.ArticleDtoRequest;
import com.emazon.stock.application.dto.ArticleDtoResponse;
import com.emazon.stock.application.dto.UpdateQuantityRequestDto;
import com.emazon.stock.domain.model.PageCustom;
public interface IArticleHandler {

        void saveArticle(ArticleDtoRequest articleDtoRequest);
        PageCustom<ArticleDtoResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName);
        void updateQuantity(UpdateQuantityRequestDto updateQuantityRequestDto);
}
