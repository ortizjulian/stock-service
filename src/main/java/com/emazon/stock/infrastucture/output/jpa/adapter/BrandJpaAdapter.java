package com.emazon.stock.infrastucture.output.jpa.adapter;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import com.emazon.stock.infrastucture.output.jpa.entity.BrandEntity;
import com.emazon.stock.infrastucture.output.jpa.mapper.BrandEntityMapper;
import com.emazon.stock.infrastucture.output.jpa.mapper.PageMapper;
import com.emazon.stock.infrastucture.output.jpa.repository.IBrandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;

public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final PageMapper pageMapper;

    public BrandJpaAdapter(IBrandRepository brandRepository, BrandEntityMapper brandEntityMapper, PageMapper pageMapper) {
        this.brandRepository = brandRepository;
        this.brandEntityMapper = brandEntityMapper;
        this.pageMapper = pageMapper;
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

    public PageCustom<Brand> getAllBrands(Integer page, Integer size, String sortDirection, String sortBy) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<BrandEntity> brandPage = brandRepository.findAll(pageable);
        return pageMapper.toBrandPageCustom(brandPage);
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
