package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.api.IBrandServicePort;
import com.emazon.stock.domain.exception.BrandAlreadyExistsException;
import com.emazon.stock.domain.exception.BrandNotFoundException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import com.emazon.stock.domain.utils.PaginationValidator;
import com.emazon.stock.utils.Constants;

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
    public PageCustom<Brand> getAllBrands(Integer page, Integer size, String sortDirection, String sortBy) {
        PaginationValidator.validatePagination(page,size,sortDirection);
        return this.brandPersistencePort.getAllBrands(page,size,sortDirection,sortBy);
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
