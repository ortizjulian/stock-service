package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.BrandDto;
import com.emazon.stock.application.mapper.BrandDtoMapper;
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
    private final BrandDtoMapper brandDtoMapper;
    private final PageDtoMapper pageDtoMapper;

    public BrandHandler(IBrandServicePort brandServicePort, BrandDtoMapper brandDtoMapper, PageDtoMapper pageDtoMapper) {
        this.brandServicePort = brandServicePort;
        this.brandDtoMapper = brandDtoMapper;
        this.pageDtoMapper = pageDtoMapper;
    }

    @Override
    public void saveBrand(BrandDto brandDto) {
        Brand brand = brandDtoMapper.toBrand(brandDto);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public PageCustom<BrandDto> getAllBrands(Integer page, Integer size, String sortDirection, String sortBy) {
        return pageDtoMapper.toBrandDtoPageCustom(brandServicePort.getAllBrands(page,size,sortDirection,sortBy));
    }

    @Override
    public void updateBrand(BrandDto brandDto) {
        Brand brand = brandDtoMapper.toBrand(brandDto);
        brandServicePort.updateBrand(brand);
    }

    @Override
    public void deleteBrand(String brandName) {
        brandServicePort.deleteBrand(brandName);
    }
}
