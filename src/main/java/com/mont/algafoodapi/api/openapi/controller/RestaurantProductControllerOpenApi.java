package com.mont.algafoodapi.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mont.algafoodapi.api.model.ProductDto;
import com.mont.algafoodapi.api.model.input.ProductInputDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Products", description = "Manage the products of restaurants")
public interface RestaurantProductControllerOpenApi {
    
    @Operation(
        summary = "List all restaurant products",
        description = "List all products in a restaurant.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))
            ),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<List<ProductDto>> findAll(@PathVariable Long restaurantId, @RequestParam(required = false) boolean includeInactive);

    @Operation(
        summary = "Find a restaurant product",
        description = "Find a restaurant product.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = ProductDto.class))
                ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<ProductDto> findById(@PathVariable Long restaurantId, @PathVariable Long productId);

    @Operation(
        summary = "Register a product in restaurant",
        description = "Register a product in restaurant.",
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(schema = @Schema(implementation = ProductDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<ProductDto> create(@PathVariable Long restaurantId, @RequestBody @Valid ProductInputDto productInputDto);

    @Operation(
        summary = "Update a product in restaurant",
        description = "Update a product in restaurant.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = ProductDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )  
    public ResponseEntity<ProductDto> update(@PathVariable Long restaurantId, @PathVariable Long productId, @RequestBody @Valid ProductInputDto productInputDto);
}
