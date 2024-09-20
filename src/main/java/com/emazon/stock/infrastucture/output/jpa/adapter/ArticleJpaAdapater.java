package com.emazon.stock.infrastucture.output.jpa.adapter;

import com.emazon.stock.domain.model.Article;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.IArticlePersistencePort;
import com.emazon.stock.infrastucture.output.jpa.entity.ArticleEntity;
import com.emazon.stock.infrastucture.output.jpa.entity.BrandEntity;
import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import com.emazon.stock.infrastucture.output.jpa.mapper.ArticleEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.mapper.PageMapper;
import com.emazon.stock.infrastucture.output.jpa.repository.IArticleRepository;
import com.emazon.stock.infrastucture.output.jpa.repository.IBrandRepository;
import com.emazon.stock.infrastucture.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ArticleJpaAdapater implements IArticlePersistencePort {

    private final IArticleRepository articleRepository;
    private final IBrandRepository brandRepository;
    private final ICategoryRepository categoryRepository;
    private final ArticleEntityMapper articleEntityMapper;
    private final PageMapper pageMapper;

    @Override
    public void saveArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toEntity(article);
        Optional<BrandEntity> optionalBrandEntity= brandRepository.findById(article.getBrand().getId());

        List<CategoryEntity> categoryEntities = article.getCategories().stream()
                .map(category -> categoryRepository.findById(category.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Category with ID " + category.getId() + " not found")))
                .toList();

        if (optionalBrandEntity.isPresent()){
            articleEntity.setBrandEntity(optionalBrandEntity.get());
            articleEntity.setArticleCategories(categoryEntities);
            this.articleRepository.save(articleEntity);
        }
    }

    @Override
    public Boolean existById(Long articleId) {
        return articleRepository.findById(articleId).isPresent();
    }

    @Override
    public PageCustom<Article> getAllArticles(Integer page, Integer size, String sortDirection, String sortBy, String brandName, String categoryName) {

        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<ArticleEntity> spec = Specification.where(null);

        if (brandName != null && !brandName.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("brandEntity").get("name"), brandName));
        }

        if (categoryName != null && !categoryName.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.join("articleCategories").get("name"), categoryName));
        }

        Page<ArticleEntity> articlePage = articleRepository.findAll(spec, pageable);

        return pageMapper.toArticlePageCustom(articlePage);
    }

    @Override
    public void updateQuantity(Long articleId, Integer quantity) {
        Optional<ArticleEntity> optionalArticleEntity = articleRepository.findById(articleId);
        if(optionalArticleEntity.isPresent()){
            ArticleEntity articleEntity = optionalArticleEntity.get();
            articleEntity.setQuantity(articleEntity.getQuantity() + quantity);

            articleRepository.save(articleEntity);
        }
    }

    @Override
    public Article getArticleById(Long articleId) {
        ArticleEntity articleEntity = articleRepository.findById(articleId).get();
        return articleEntityMapper.toArticle(articleEntity);
    }

    @Override
    public List<Article> getArticlesByIds(List<Integer> articlesIds) {
        List<Long> articleIdsLong = articlesIds.stream()
                .map(Integer::longValue)
                .toList();

        List<ArticleEntity> articleEntities = articleRepository.findAllById(articleIdsLong);

        return articleEntityMapper.toArticleList(articleEntities);
    }

}
