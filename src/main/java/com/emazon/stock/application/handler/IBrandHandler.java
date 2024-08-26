package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.BrandDto;

import java.util.List;

public interface IBrandHandler {
    void saveBrand(BrandDto brandDto);
    List<BrandDto> getAllBrands();
    void updateBrand(BrandDto brandDto);
    void deleteBrand(String brandName);
}
