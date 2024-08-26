package com.emazon.stock.domain.api;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.PageCustom;

public interface IBrandServicePort {
    void saveBrand(Brand brand);
    PageCustom<Brand> getAllBrands(Integer page, Integer size, String sortDirection, String sortBy);
    void updateBrand(Brand brand);
    void deleteBrand(String brandName);
}
