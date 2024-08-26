package com.emazon.stock.application.mapper;

import com.emazon.stock.application.dto.BrandDto;
import com.emazon.stock.application.dto.CategoryDtoResponse;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PageDtoMapper {

    private final CategoryDtoResponseMapper categoryDtoResponseMapper;
    private final BrandDtoMapper brandDtoMapper;

    public PageCustom<CategoryDtoResponse> toCategoryDtoPageCustom(PageCustom<Category> page) {
        PageCustom<CategoryDtoResponse> pageCustom = new PageCustom<>();
        List<CategoryDtoResponse> categories = page.getContent().stream()
                .map(categoryDtoResponseMapper::toCategoryDtoResponse)
                .toList();

        pageCustom.setContent(categories);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.getHasNext());
        pageCustom.setHasPrevious(page.getHasPrevious());
        return pageCustom;
    }

    public PageCustom<BrandDto> toBrandDtoPageCustom(PageCustom<Brand> page) {
        PageCustom<BrandDto> pageCustom = new PageCustom<>();
        List<BrandDto> brands = page.getContent().stream()
                .map(brandDtoMapper::toBrandDto)
                .toList();

        pageCustom.setContent(brands);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.getHasNext());
        pageCustom.setHasPrevious(page.getHasPrevious());
        return pageCustom;
    }
}
