package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.BrandDtoRequest;
import com.emazon.stock.application.dto.BrandDtoResponse;
import com.emazon.stock.domain.model.PageCustom;

public interface IBrandHandler {
    void saveBrand(BrandDtoRequest brandDtoRequest);
    PageCustom<BrandDtoResponse> getAllBrands(Integer page, Integer size, String sortDirection, String sortBy);
    void updateBrand(Long brandId,BrandDtoRequest brandDtoRequest);
    void deleteBrand(Long brandId);
}
