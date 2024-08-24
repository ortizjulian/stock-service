package com.emazon.stock.infrastucture.input.rest;

import com.emazon.stock.application.dto.CategoryDto;
import com.emazon.stock.application.handler.ICategoryHandler;
import com.emazon.stock.domain.model.PageCustom;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
@Validated
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Retrieve all categories", description = "Returns a list of all categories available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of categories"),
    })
    @GetMapping
    public ResponseEntity<PageCustom<CategoryDto>> getAllCategories(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue= "name") String sortBy
    ){
        return ResponseEntity.ok(categoryHandler.getAllCategories(page,size,sortDirection,sortBy));
    }

    @Operation(summary = "Create a new category", description = "Adds a new category to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Category already exists"),
    })
    @PostMapping
    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
        categoryHandler.saveCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an existing category", description = "Updates the details of an existing category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category updated successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
    })
    @PutMapping
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryDto categoryDto) {
        categoryHandler.updateCategory(categoryDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a category by name", description = "Deletes an existing category from the system using its name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Category not found"),
    })
    @DeleteMapping("/{categoryName}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryName) {
        categoryHandler.deleteCategory(categoryName);
        return  ResponseEntity.noContent().build();
    }

}
