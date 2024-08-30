package com.emazon.stock.application.mapper;

import com.emazon.stock.application.dto.CategoryDtoResponse;
import com.emazon.stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoResponseMapper {

    CategoryDtoResponse toCategoryDtoResponse(Category category);
}