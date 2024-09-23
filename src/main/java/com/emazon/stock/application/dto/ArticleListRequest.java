package com.emazon.stock.application.dto;

import java.util.List;

public class ArticleListRequest {
    private List<Long> articleIds;

    public ArticleListRequest() {
    }

    public ArticleListRequest(List<Long> articleIds) {
        this.articleIds = articleIds;
    }

    public List<Long> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<Long> articleIds) {
        this.articleIds = articleIds;
    }
}
