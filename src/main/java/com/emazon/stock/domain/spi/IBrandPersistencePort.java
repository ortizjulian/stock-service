package com.emazon.stock.domain.spi;

import com.emazon.stock.domain.model.Brand;

import java.util.List;

public interface IBrandPersistencePort {
    void saveBrand(Brand brand);
    Boolean findByName(String brandName);
    List<Brand> getAllBrands();
    void updateBrand(Brand brand);
    void deleteBrand(String brandName);
}
