package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.exception.DataConstraintViolationException;
import com.emazon.stock.domain.exception.MissingAttributeException;
import com.emazon.stock.domain.exception.PaginationParametersInvalidException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryUseCaseTest {


    @Mock
    private  ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void CategoryUseCase_SaveCategory_ShouldCallSaveCategoryOnPersistencePort() {
        Category newCategory = new Category(null,"Computadores","Todo lo relacionado a computadores");
        Mockito.doNothing().when(categoryPersistencePort).saveCategory(newCategory);
        categoryUseCase.saveCategory(newCategory);
        Mockito.verify(categoryPersistencePort).saveCategory(newCategory);
    }

    @Test
    void CategoryUseCase_SaveCategory_WhenNameIsNull_ShouldThrowMissingAttributeException() {
        Category invalidCategory = new Category(null,null,"Todo lo relacionado a computadores");

        assertThrows(MissingAttributeException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });
    }

    @Test
    void CategoryUseCase_SaveCategory_WhenCategoryIsNull_ShouldThrowMissingAttributeException() {
        Category invalidCategory = new Category(null, "Nombre Categoría", null);

        assertThrows(MissingAttributeException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });
    }

    @Test
    void CategoryUseCase_SaveCategory_WhenNameExceeds50Characters_ShouldThrowDataConstraintViolationException() {
        Category invalidCategory = new Category(null,"Nombre de la categoría muy largo para probar que la Excepción DataConstraintViolationException sea lanzada","Todo lo relacionado a computadores");

        assertThrows(DataConstraintViolationException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });
    }

    @Test
    void CategoryUseCase_SaveCategory_WhenDescriptionExceeds90Characters_ShouldThrowDataConstraintViolationException() {
        Category invalidCategory = new Category(null,
                "Nombre de la categoría válido",
                "Descripción de la categoría que excede los noventa caracteres para probar que la Excepción DataConstraintViolationException sea lanzada. Esto es una descripción muy larga para realizar los test.");

        assertThrows(DataConstraintViolationException.class, () -> {
            categoryUseCase.saveCategory(invalidCategory);
        });
    }

    @Test
    void CategoryUseCase_GetAllCategories_WhenPageIsNegative_ShouldThrowPaginationParametersInvalidException() {

        int invalidPage = -1;
        int size = 10;
        String sortDirection = "ASC";
        String sortBy = "name";

        assertThrows(PaginationParametersInvalidException.class, () -> {
            categoryUseCase.getAllCategories(invalidPage, size, sortDirection, sortBy);
        });
    }

    @Test
    void CategoryUseCase_GetAllCategories_ShouldReturnCategoriesSortedByNameAscending() {

        int page = 0;
        int size = 10;
        String sortDirection = "ASC";
        String sortBy = "name";

        List<Category> categories = new ArrayList<Category>();
        categories.add( new Category(1L, "Computadores", "Todo lo relacionado a computadores"));
        categories.add( new Category(2L, "Gimnasio", "Todo lo relacionado a gimnasio"));
        categories.add( new Category(3L, "Vehículo", "Todo lo relacionado a vehículos"));

        PageCustom<Category> mockPage = new PageCustom<>();
        mockPage.setContent(categories);
        mockPage.setTotalElements(3L);
        mockPage.setTotalPages(1);

        Mockito.when(categoryPersistencePort.getAllCategories(page, size, sortDirection, sortBy))
                .thenReturn(mockPage);


        PageCustom<Category> result = categoryUseCase.getAllCategories(page, size, sortDirection, sortBy);


        assertNotNull(result);
        assertEquals(3, result.getContent().size());
        assertEquals("Computadores", result.getContent().get(0).getName());
        assertEquals("Gimnasio", result.getContent().get(1).getName());
        assertEquals("Vehículo", result.getContent().get(2).getName());

        Mockito.verify(categoryPersistencePort).getAllCategories(page, size, sortDirection, sortBy);
    }
}