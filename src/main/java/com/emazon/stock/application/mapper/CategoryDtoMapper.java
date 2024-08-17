package com.emazon.stock.application.mapper;

import com.emazon.stock.application.dto.CategoryDto;
import com.emazon.stock.domain.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

    Category toCategory(CategoryDto categoryDto);
    List<CategoryDto> toDtoList(List<Category> categoryList);
}