package com.emazon.stock.infrastucture.output.jpa.mapper;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    CategoryEntity toEntity(Category category);

    Category toCategory(CategoryEntity categoryEntity);
}

