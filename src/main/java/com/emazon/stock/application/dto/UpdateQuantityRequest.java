package com.emazon.stock.application.dto;

import com.emazon.stock.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateQuantityRequest {

    @NotNull(message = Constants.EXCEPTION_ARTICLE_ID_NULL)
    private Long articleId;

    @NotNull(message = Constants.EXCEPTION_ARTICLE_QUANTITY_NULL)
    @Positive(message = Constants.EXCEPTION_ARTICLE_QUANTITY_POSITIVE)
    private Integer quantity;

    public UpdateQuantityRequest(Long articleId, Integer quantity) {
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
