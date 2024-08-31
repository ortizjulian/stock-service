package com.emazon.stock.infrastucture.input.rest;


import com.emazon.stock.application.dto.BrandDtoRequest;
import com.emazon.stock.application.dto.BrandDtoResponse;
import com.emazon.stock.application.handler.IBrandHandler;
import com.emazon.stock.domain.model.PageCustom;
import com.emazon.stock.utils.Constants;
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
    public ResponseEntity<PageCustom<BrandDtoResponse>> getAllBrands(
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) Integer size,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_DIRECTION) String sortDirection,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_BY) String sortBy
    ){
        return ResponseEntity.ok(brandHandler.getAllBrands(page,size,sortDirection,sortBy));
    }

    @Operation(summary = "Create a new brand", description = "Adds a new brand to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Brand already exists"),
    })
    @PostMapping
    public ResponseEntity<Void> saveBrand(@Valid @RequestBody BrandDtoRequest brandDtoRequest) {
        brandHandler.saveBrand(brandDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update an existing brand", description = "Updates the details of an existing brand.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand updated successfully"),
            @ApiResponse(responseCode = "404", description = "Brand not found"),
    })
    @PutMapping("/{brandId}")
    public ResponseEntity<Void> updateBrand(@PathVariable Long brandId,@Valid @RequestBody BrandDtoRequest brandDtoRequest) {
        brandHandler.updateBrand(brandId,brandDtoRequest);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete a brand by name", description = "Deletes an existing brand from the system using its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Brand deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Brand not found"),
    })
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Long categoryId) {
        brandHandler.deleteBrand(categoryId);
        return ResponseEntity.noContent().build();
    }
}
