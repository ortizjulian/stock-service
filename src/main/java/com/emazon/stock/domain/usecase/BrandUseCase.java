package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.exception.BrandAlreadyExistsException;
import com.emazon.stock.domain.exception.BrandNotFoundException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import com.emazon.stock.utils.Constants;

import java.util.List;

public class BrandUseCase implements IBrandServicePort {

    private final IBrandPersistencePort brandPersistencePort;

    public BrandUseCase(IBrandPersistencePort brandPersistencePort) {
        this.brandPersistencePort = brandPersistencePort;
    }

    @Override
    public void saveBrand(Brand brand) {
        if(brandPersistencePort.findByName(brand.getName())){
            throw new BrandAlreadyExistsException();
        }
        this.brandPersistencePort.saveBrand(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return this.brandPersistencePort.getAllBrands();
    }

    @Override
    public void updateBrand(Brand brand) {
        if(!this.brandPersistencePort.findByName(brand.getName())){
            throw new BrandNotFoundException(Constants.EXCEPTION_BRAND_NOT_FOUND + brand.getName());
        }
        this.brandPersistencePort.updateBrand(brand);
    }

    @Override
    public void deleteBrand(String brandName) {
        if(!this.brandPersistencePort.findByName(brandName)){
            throw new BrandNotFoundException(Constants.EXCEPTION_BRAND_NOT_FOUND + brandName);
        }
        this.brandPersistencePort.deleteBrand(brandName);
    }
}
