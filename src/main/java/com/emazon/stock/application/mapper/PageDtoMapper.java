package com.emazon.stock.application.mapper;

import com.emazon.stock.application.dto.BrandDtoResponse;
import com.emazon.stock.application.dto.CategoryDtoResponse;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CategoryDtoResponseMapper.class,BrandDtoResponseMapper.class })
public interface  PageDtoMapper {

     CategoryDtoResponseMapper CATEGORY_DTO_RESPONSE_MAPPER = Mappers.getMapper(CategoryDtoResponseMapper.class);
     BrandDtoResponseMapper BRAND_DTO_RESPONSE_MAPPER = Mappers.getMapper(BrandDtoResponseMapper.class);

    default PageCustom<CategoryDtoResponse> toCategoryDtoPageCustom(PageCustom<Category> page) {
        PageCustom<CategoryDtoResponse> pageCustom = new PageCustom<>();
        List<CategoryDtoResponse> categories = page.getContent().stream()
                .map(CATEGORY_DTO_RESPONSE_MAPPER::toCategoryDtoResponse)
                .toList();

        pageCustom.setContent(categories);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.getHasNext());
        pageCustom.setHasPrevious(page.getHasPrevious());
        return pageCustom;
    }

    default PageCustom<BrandDtoResponse> toBrandDtoPageCustom(PageCustom<Brand> page) {
        PageCustom<BrandDtoResponse> pageCustom = new PageCustom<>();
        List<BrandDtoResponse> brands = page.getContent().stream()
                .map(BRAND_DTO_RESPONSE_MAPPER::toBrandDtoResponse)
                .toList();

        pageCustom.setContent(brands);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.getHasNext());
        pageCustom.setHasPrevious(page.getHasPrevious());
        return pageCustom;
    }
}
