package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.PageCustom;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Boolean findByName(String brandName);
    PageCustom<Brand> getAllBrands(Integer page, Integer size, String sortDirection, String sortBy);
    void updateBrand(Brand brand);
    void deleteBrand(String brandName);
}
