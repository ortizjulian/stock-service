package com.emazon.stock.infrastucture.input.rest;


import com.emazon.stock.application.dto.BrandDto;
import com.emazon.stock.application.handler.IBrandHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
@Validated
public class BrandRestController {

    private final IBrandHandler brandHandler;

    @Operation(summary = "Retrieve all brands", description = "Returns a list of all brands available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of brands"),
    })
    @GetMapping
    public ResponseEntity<List<BrandDto>> getAllBrands(

    ){
        return ResponseEntity.ok(brandHandler.getAllBrands());
    }

    @Operation(summary = "Create a new brand", description = "Adds a new brand to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Brand already exists"),
    })
    @PostMapping
    public ResponseEntity<Void> saveBrand(@Valid @RequestBody BrandDto brandDto) {
        brandHandler.saveBrand(brandDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an existing brand", description = "Updates the details of an existing brand.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand updated successfully"),
            @ApiResponse(responseCode = "404", description = "Brand not found"),
    })
    @PutMapping
    public ResponseEntity<Void> updateBrand(@Valid @RequestBody BrandDto brandDto) {
        brandHandler.updateBrand(brandDto);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a brand by name", description = "Deletes an existing brand from the system using its name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Brand not found"),
    })
    @DeleteMapping("/{brandName}")
    public ResponseEntity<Void> deleteBrand(@PathVariable String brandName) {
        brandHandler.deleteBrand(brandName);
        return ResponseEntity.noContent().build();
    }
}
