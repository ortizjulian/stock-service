package com.emazon.stock.infrastucture.output.jpa.mapper;

import com.emazon.stock.domain.model.Article;
import com.emazon.stock.infrastucture.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleEntityMapper {

    @Mapping(source = "brandEntity", target = "brand")
    @Mapping(source = "articleCategories", target = "categories")
    Article toArticle(ArticleEntity articleEntity);

    @Mapping(source = "brand", target = "brandEntity")
    @Mapping(source = "categories", target = "articleCategories")
    ArticleEntity toEntity(Article article);

    List<Article> toArticleList(List<ArticleEntity> articleEntities);
}
