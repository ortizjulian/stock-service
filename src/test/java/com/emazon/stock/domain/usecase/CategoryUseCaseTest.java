package com.emazon.stock.domain.usecase;

import com.emazon.stock.domain.exception.DataConstraintViolationException;
import com.emazon.stock.domain.exception.MissingAttributeException;
import com.emazon.stock.domain.model.Category;
import com.emazon.stock.domain.spi.ICategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
}