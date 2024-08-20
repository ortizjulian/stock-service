package com.emazon.stock.infrastucture.input.rest;

import com.emazon.stock.application.dto.CategoryDto;
import com.emazon.stock.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.ok(categoryHandler.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<Void> saveCategory(@RequestBody CategoryDto categoryDto) {
        categoryHandler.saveCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryDto categoryDto) {
        categoryHandler.updateCategory(categoryDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{categoryName}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String categoryName) {
        categoryHandler.deleteCategory(categoryName);
        return  ResponseEntity.noContent().build();
    }

}
