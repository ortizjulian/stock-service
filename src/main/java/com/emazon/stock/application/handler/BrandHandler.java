package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.BrandDtoRequest;
import com.emazon.stock.application.dto.BrandDtoResponse;
import com.emazon.stock.application.mapper.BrandDtoRequestMapper;
import com.emazon.stock.application.mapper.PageDtoMapper;
import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.PageCustom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BrandHandler implements IBrandHandler{

    private final IBrandServicePort brandServicePort;
    private final BrandDtoRequestMapper brandDtoRequestMapper;
    private final PageDtoMapper pageDtoMapper;

    public BrandHandler(IBrandServicePort brandServicePort, BrandDtoRequestMapper brandDtoRequestMapper, PageDtoMapper pageDtoMapper) {
        this.brandServicePort = brandServicePort;
        this.brandDtoRequestMapper = brandDtoRequestMapper;
        this.pageDtoMapper = pageDtoMapper;
    }

    @Override
    public void saveBrand(BrandDtoRequest brandDtoRequest) {
        Brand brand = brandDtoRequestMapper.toBrand(brandDtoRequest);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public PageCustom<BrandDtoResponse> getAllBrands(Integer page, Integer size, String sortDirection, String sortBy) {
        return pageDtoMapper.toBrandDtoPageCustom(brandServicePort.getAllBrands(page,size,sortDirection,sortBy));
    }

    @Override
    public void updateBrand(Long brandId,BrandDtoRequest brandDtoRequest) {
        Brand brand = brandDtoRequestMapper.toBrand(brandDtoRequest);
        brandServicePort.updateBrand(brandId, brand);
    }

    @Override
    public void deleteBrand(Long brandId) {
        brandServicePort.deleteBrand(brandId);
    }
}
