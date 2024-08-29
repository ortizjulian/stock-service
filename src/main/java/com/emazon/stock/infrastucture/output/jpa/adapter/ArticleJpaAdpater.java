package com.emazon.stock.infrastucture.output.jpa.adapter;

import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.spi.IArticlePersistencePort;
import com.emazon.stock.infrastucture.output.jpa.entity.ArticleEntity;
import com.emazon.stock.infrastucture.output.jpa.entity.BrandEntity;
import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import com.emazon.stock.infrastucture.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.repository.IArticleRepository;
import com.emazon.stock.infrastucture.output.jpa.repository.IBrandRepository;
import com.emazon.stock.infrastucture.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ArticleJpaAdpater implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final IBrandRepository brandRepository;
    private final ICategoryRepository categoryRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Override
    public void saveArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);
        Optional<BrandEntity> optionalBrandEntity= brandRepository.findById(article.getBrand().getId());

        Set<CategoryEntity> categoryEntities = article.getCategories().stream()
                .map(category -> categoryRepository.findById(category.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Category with ID " + category.getId() + " not found")))
                .collect(Collectors.toSet());

        if (optionalBrandEntity.isPresent()){
            articleEntity.setBrandEntity(optionalBrandEntity.get());
            articleEntity.setArticleCategories(categoryEntities);
            this.articleRepository.save(articleEntity);
        }
    }

    @Override
    public List<Article> getAllArticles() {
        List<ArticleEntity> articleEntityList = articleRepository.findAll();
        return articleEntityMapper.toArticleList(articleEntityList);
    }

}
