package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.ArticleDtoRequest;
import com.emazon.stock.application.dto.ArticleDtoResponse;
import com.emazon.stock.application.mapper.ArticleDtoRequestMapper;
import com.emazon.stock.application.mapper.ArticleDtoResponseMapper;
import com.emazon.stock.domain.api.IArticleServicePort;
import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler{

    private final IArticleServicePort articleServicePort;
    private final ArticleDtoRequestMapper articleDtoRequestMapper;
    private final ArticleDtoResponseMapper articleDtoResponseMapper;

    @Override
    public void saveArticle(ArticleDtoRequest articleDtoRequest) {

        Article article = articleDtoRequestMapper.articleDtoRequestToArticle(articleDtoRequest);

        Set<Category> categories = articleDtoRequest.getCategoryIds().stream()
                .map(categoryId -> {
                    Category category = new Category();
                    category.setId(categoryId);
                    return category;
                }).collect(Collectors.toSet());

        Brand brand = new Brand();
        brand.setId(articleDtoRequest.getBrandId());

        article.setBrand(brand);
        article.setCategories(categories);

        this.articleServicePort.saveArticle(article);

    }

    @Override
    public List<ArticleDtoResponse> getAllArticles() {
        List<Article> articleList = this.articleServicePort.getAllArticles();
        return articleDtoResponseMapper.toArticleDtoResponseList(articleList);
    }

    @Override
    public void updateArticle(Long articleId, ArticleDtoRequest articleDtoRequest) {
        Article article = articleDtoRequestMapper.articleDtoRequestToArticle(articleDtoRequest);

        this.articleServicePort.updateArticle(articleId,article);
    }

    @Override
    public void deleteArticle(Long articleId) {
        this.articleServicePort.deleteArticle(articleId);

    }
}
