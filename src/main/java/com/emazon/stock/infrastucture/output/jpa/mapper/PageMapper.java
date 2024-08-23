package com.emazon.stock.infrastucture.output.jpa.mapper;

import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.infrastucture.output.jpa.entity.CategoryEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
public class PageMapper {

    private final CategoryEntityMapper categoryEntityMapper;

    public PageCustom<Category> toCategoryPageCustom(Page<CategoryEntity> page) {
        PageCustom<Category> pageCustom = new PageCustom<>();
        List<Category> categories = page.getContent().stream()
                .map(categoryEntityMapper::toCategory)
                .toList();

        pageCustom.setContent(categories);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());

        return pageCustom;
    }
}
