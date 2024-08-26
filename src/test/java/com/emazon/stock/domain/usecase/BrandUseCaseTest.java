package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class BrandUseCaseTest {

    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private BrandUseCase brandUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void BrandUseCase_SaveBrand_ShouldCallSaveBrandOnPersistencePort() {
        Brand newBrand = new Brand(null, "Mattelsa", "Ropa de marca colombiana");
        Mockito.doNothing().when(brandPersistencePort).saveBrand(newBrand);
        brandUseCase.saveBrand(newBrand);
        Mockito.verify(brandPersistencePort).saveBrand(newBrand);
    }

}