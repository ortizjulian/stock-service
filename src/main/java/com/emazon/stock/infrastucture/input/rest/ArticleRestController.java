package com.emazon.stock.infrastucture.input.rest;

import com.emazon.stock.application.dto.*;
import com.emazon.stock.application.handler.IArticleHandler;
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
    public ResponseEntity<PageCustom<ArticleDtoResponse>> getAllArticles(
            @RequestParam(defaultValue = Constants.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = Constants.DEFAULT_SIZE) Integer size,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_DIRECTION) String sortDirection,
            @RequestParam(defaultValue = Constants.DEFAULT_SORT_BY) String sortBy,
            @RequestParam(defaultValue = Constants.DEFAULT_BRAND_NAME) String brandName,
            @RequestParam(defaultValue = Constants.DEFAULT_CATEGORY_NAME) String categoryName,
            @RequestParam(required = false) List<Long> articleIds
    ){
        return ResponseEntity.ok(articleHandler.getAllArticles(page,size,sortDirection,sortBy,brandName,categoryName,articleIds));
    }

    @Operation(summary = "Create a new Article", description = "Adds a new Article to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Article already exists"),
    })
    @PostMapping
    public ResponseEntity<Void> saveArticle(@Valid @RequestBody ArticleDtoRequest articleDtoRequest) {
        articleHandler.saveArticle(articleDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Update Article Quantity", description = "Update the quantity of an existing article.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update quantity successful"),
            @ApiResponse(responseCode = "404", description = "Not article found with given id"),
            @ApiResponse(responseCode = "400", description = "Bad request body")
    })
    @PatchMapping("/updateQuantity")
    public ResponseEntity<Void> updateArticleQuantity(@Valid @RequestBody UpdateQuantityRequest updateQuantityRequest){
        articleHandler.updateQuantity(updateQuantityRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Check Article Stock", description = "Retrieve the stock quantity of a specific article.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Article not found with the given id")
    })
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDtoResponse> checkArticleStock(@PathVariable Long articleId) {
        ArticleDtoResponse articleDtoResponse = articleHandler.getArticleById(articleId);
        return ResponseEntity.ok(articleDtoResponse);
    }

    @Operation(
            summary = "Retrieve total price by article IDs",
            description = "Calculates and returns the total price for a list of articles based on their IDs."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total price calculated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body. Please ensure the article list is provided."),
            @ApiResponse(responseCode = "404", description = "One or more articles not found. Please verify the article IDs.")
    })
    @PostMapping("/price")
    public ResponseEntity<PriceDto> getTotalPriceByArticleIds(
            @RequestBody ArticleListRequest articleListRequest) {
        PriceDto price = articleHandler.getTotalPriceByArticleIds(articleListRequest.getArticleIds());
        return ResponseEntity.ok(price);
    }
}
