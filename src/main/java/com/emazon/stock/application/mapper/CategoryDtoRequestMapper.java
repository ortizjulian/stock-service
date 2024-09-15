package com.emazon.stock.application.mapper;

import com.emazon.stock.application.dto.CategoryDtoRequest;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = Constants.MAPPER_STRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoRequestMapper {

    Category toCategory(CategoryDtoRequest categoryDtoRequest);
}