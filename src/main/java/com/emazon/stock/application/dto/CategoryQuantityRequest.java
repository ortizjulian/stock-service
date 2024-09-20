package com.emazon.stock.application.dto;

import java.util.List;

public class CategoryQuantityRequest {
    private List<Integer> articleIds;

    public CategoryQuantityRequest() {
    }

    public CategoryQuantityRequest(List<Integer> articleIds) {
        this.articleIds = articleIds;
    }

    public List<Integer> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<Integer> articleIds) {
        this.articleIds = articleIds;
    }
}
