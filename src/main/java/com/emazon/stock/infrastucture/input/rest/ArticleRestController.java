package com.emazon.stock.infrastucture.input.rest;

import com.emazon.stock.application.dto.ArticleDtoRequest;
import com.emazon.stock.application.dto.ArticleDtoResponse;
import com.emazon.stock.application.handler.IArticleHandler;
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
@RequestMapping("article")
@RequiredArgsConstructor
@Validated
public class ArticleRestController {

    private final IArticleHandler articleHandler;


    @Operation(summary = "Retrieve all Articles", description = "Returns a list of all Article available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of Article"),
    })
    @GetMapping
    public ResponseEntity<List<ArticleDtoResponse>> getAllBrands(

    ){
        return ResponseEntity.ok(articleHandler.getAllArticles());
    }

    @Operation(summary = "Create a new Article", description = "Adds a new Article to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Article already exists"),
    })
    @PostMapping
    public ResponseEntity<Void> saveBrand(@Valid @RequestBody ArticleDtoRequest articleDtoRequest) {
        articleHandler.saveArticle(articleDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
