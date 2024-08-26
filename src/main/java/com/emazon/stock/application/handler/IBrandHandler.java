package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.BrandDto;
import com.emazon.stock.domain.model.PageCustom;

public interface IBrandHandler {
    void saveBrand(BrandDto brandDto);
    PageCustom<BrandDto> getAllBrands(Integer page, Integer size, String sortDirection, String sortBy);
    void updateBrand(BrandDto brandDto);
    void deleteBrand(String brandName);
}
