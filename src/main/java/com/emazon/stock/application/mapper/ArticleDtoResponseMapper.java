package com.emazon.stock.application.mapper;

import com.emazon.stock.application.dto.ArticleDtoResponse;
import com.emazon.stock.domain.model.Article;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleDtoResponseMapper {

    List<ArticleDtoResponse> toArticleDtoResponseList(List<Article> articles);
}
