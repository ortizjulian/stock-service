package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.ArticleDtoRequest;
import com.emazon.stock.application.dto.ArticleDtoResponse;
import com.emazon.stock.application.mapper.ArticleDtoRequestMapper;
import com.emazon.stock.application.mapper.ArticleDtoResponseMapper;
import com.emazon.stock.application.mapper.PageDtoMapper;
import com.emazon.stock.domain.api.IArticleServicePort;
import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleHandler implements IArticleHandler{

    private final IArticleServicePort articleServicePort;
    private final ArticleDtoRequestMapper articleDtoRequestMapper;
    private final ArticleDtoResponseMapper articleDtoResponseMapper;
    private final PageDtoMapper pageDtoMapper;

    @Override
    public void saveArticle(ArticleDtoRequest articleDtoRequest) {

        Article article = articleDtoRequestMapper.articleDtoRequestToArticle(articleDtoRequest);

        List<Category> categories = articleDtoRequest.getCategoryIds().stream()
                .map(categoryId -> {
                    Category category = new Category();
                    category.setId(categoryId);
                    return category;
                }).toList();

        Brand brand = new Brand();
        brand.setId(articleDtoRequest.getBrandId());

        article.setBrand(brand);
        article.setCategories(categories);

        this.articleServicePort.saveArticle(article);

    }

    @Override
    public PageCustom<ArticleDtoResponse> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName) {
        PageCustom<Article> articleList = this.articleServicePort.getAllArticles(page,size,sortDirection,sortBy,brandName,categoryName);
        return pageDtoMapper.toArticleDtoPageCustom(articleList);
    }

}
