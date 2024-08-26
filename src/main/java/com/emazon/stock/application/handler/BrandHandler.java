package com.emazon.stock.application.handler;

import com.emazon.stock.application.dto.BrandDto;
import com.emazon.stock.application.mapper.BrandDtoMapper;
import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.model.Brand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BrandHandler implements IBrandHandler{

    private final IBrandServicePort brandServicePort;
    private final BrandDtoMapper brandDtoMapper;

    public BrandHandler(IBrandServicePort brandServicePort, BrandDtoMapper brandDtoMapper) {
        this.brandServicePort = brandServicePort;
        this.brandDtoMapper = brandDtoMapper;
    }

    @Override
    public void saveBrand(BrandDto brandDto) {
        Brand brand = brandDtoMapper.toBrand(brandDto);
        brandServicePort.saveBrand(brand);
    }

    @Override
    public List<BrandDto> getAllBrands() {
        return brandDtoMapper.toDtoList(brandServicePort.getAllBrands());
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
