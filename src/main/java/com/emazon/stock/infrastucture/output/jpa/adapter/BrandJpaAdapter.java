package com.emazon.stock.infrastucture.output.jpa.adapter;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import com.emazon.stock.infrastucture.output.jpa.entity.BrandEntity;
import com.emazon.stock.infrastucture.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.repository.IBrandRepository;

import java.util.List;
import java.util.Optional;

public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;

    public BrandJpaAdapter(IBrandRepository brandRepository, BrandEntityMapper brandEntityMapper) {
        this.brandRepository = brandRepository;
        this.brandEntityMapper = brandEntityMapper;
    }

    @Override
    public void saveBrand(Brand brand) {
        BrandEntity brandEntity = brandEntityMapper.toEntity(brand);
        brandRepository.save(brandEntity);

    }

    @Override
    public Boolean findByName(String brandName) {
        return brandRepository.findByName(brandName).isPresent();
    }

    @Override
    public List<Brand> getAllBrands() {
        List<BrandEntity> brandEntityList = brandRepository.findAll();
        return brandEntityMapper.toBrandList(brandEntityList);
    }

    @Override
    public void updateBrand(Brand brand) {
        Optional<BrandEntity> optionalBrandEntity = brandRepository.findByName(brand.getName());

        if(optionalBrandEntity.isPresent()){
            BrandEntity brandEntity = optionalBrandEntity.get();
            brandEntity.setDescription(brand.getDescription());
            brandRepository.save(brandEntity);
        }
    }

    @Override
    public void deleteBrand(String brandName) {
        brandRepository.deleteByName(brandName);
    }
}
