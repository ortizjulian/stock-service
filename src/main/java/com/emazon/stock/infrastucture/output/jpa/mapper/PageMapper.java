package com.emazon.stock.infrastucture.output.jpa.mapper;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.infrastucture.output.jpa.entity.BrandEntity;
import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
public class PageMapper {

    private final CategoryEntityMapper categoryEntityMapper;
    private final BrandEntityMapper brandEntityMapper;

    public PageCustom<Category> toCategoryPageCustom(Page<CategoryEntity> page) {
        PageCustom<Category> pageCustom = new PageCustom<>();
        List<Category> categories = page.getContent().stream()
                .map(categoryEntityMapper::toCategory)
                .toList();

        pageCustom.setContent(categories);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.hasNext());
        pageCustom.setHasPrevious(page.hasPrevious());
        return pageCustom;
    }

    public PageCustom<Brand> toBrandPageCustom(Page<BrandEntity> page) {
        PageCustom<Brand> pageCustom = new PageCustom<>();
        List<Brand> brands = page.getContent().stream()
                .map(brandEntityMapper::toBrand)
                .toList();

        pageCustom.setContent(brands);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setHasNext(page.hasNext());
        pageCustom.setHasPrevious(page.hasPrevious());
        return pageCustom;
    }
}
