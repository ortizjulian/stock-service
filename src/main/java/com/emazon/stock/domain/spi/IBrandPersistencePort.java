package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.PageCustom;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Boolean existsByName(String brandName);
    Boolean existById(Long brandId);
    PageCustom<Brand> getAllBrands(Integer page, Integer size, String sortDirection, String sortBy);
    void updateBrand(Long brandId,Brand brand);
    void deleteBrand(Long brandId);
}
