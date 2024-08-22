package com.emazon.stock.application.mapper;

import com.emazon.stock.application.dto.CategoryDto;
import com.emazon.stock.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoMapper {

    Category toCategory(CategoryDto categoryDto);
    List<CategoryDto> toDtoList(List<Category> categoryList);
}