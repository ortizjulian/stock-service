package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.exception.PaginationParametersInvalidException;
import com.emazon.stock.domain.model.Brand;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.IBrandPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void BrandUseCase_GetAllBrands_WhenPageIsNegative_ShouldThrowPaginationParametersInvalidException() {

        int invalidPage = -1;
        int size = 10;
        String sortDirection = "ASC";
        String sortBy = "name";

        assertThrows(PaginationParametersInvalidException.class, () -> {
            brandUseCase.getAllBrands(invalidPage, size, sortDirection, sortBy);
        });
    }

    @Test
    void BrandUseCase_GetAllBrands_ShouldReturnCategoriesSortedByNameAscending() {

        int page = 0;
        int size = 10;
        String sortDirection = "ASC";
        String sortBy = "name";

        List<Brand> brands = new ArrayList<Brand>();
        brands.add( new Brand(1L, "Adidas", "Todo lo relacionado a Adidas"));
        brands.add( new Brand(2L, "Mattelsa", "Todo lo relacionado a Mattelsa"));
        brands.add( new Brand(3L, "Zara", "Todo lo relacionado a Zara"));

        PageCustom<Brand> mockPage = new PageCustom<>();
        mockPage.setContent(brands);
        mockPage.setTotalElements(3L);
        mockPage.setTotalPages(1);

        Mockito.when(brandPersistencePort.getAllBrands(page, size, sortDirection, sortBy))
                .thenReturn(mockPage);


        PageCustom<Brand> result = brandUseCase.getAllBrands(page, size, sortDirection, sortBy);


        assertNotNull(result);
        assertEquals(3, result.getContent().size());
        assertEquals("Adidas", result.getContent().get(0).getName());
        assertEquals("Mattelsa", result.getContent().get(1).getName());
        assertEquals("Zara", result.getContent().get(2).getName());

        Mockito.verify(brandPersistencePort).getAllBrands(page, size, sortDirection, sortBy);
    }

}