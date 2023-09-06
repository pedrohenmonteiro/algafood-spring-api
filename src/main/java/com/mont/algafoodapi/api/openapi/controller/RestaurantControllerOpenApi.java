package com.mont.algafoodapi.api.openapi.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mont.algafoodapi.api.model.RestaurantDto;
import com.mont.algafoodapi.api.model.input.RestaurantInputDto;
import com.mont.algafoodapi.api.openapi.model.RestaurantBasicModelOpenApi;
import com.mont.algafoodapi.domain.model.Restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Restaurants", description = "Manage restaurants")
public interface RestaurantControllerOpenApi {
    
    @Parameter(name = "proj", in = ParameterIn.QUERY, description = "Name of restaurant projection", schema = @Schema(type = "string", allowableValues = {"name"}))
    @Operation(
        summary = "List all restaurants",
        description = "List all restaurants.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(
                   array = @ArraySchema(schema = @Schema(implementation = RestaurantBasicModelOpenApi.class))
                )
            ),
            @ApiResponse( description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<List<RestaurantDto>> findAll();

    @Operation(hidden = true)
    public ResponseEntity<List<RestaurantDto>> findAllName();

    @Operation(
        summary = "Find a restaurant by id",
        description = "Finds a restaurant by their Id.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = RestaurantDto.class))
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<RestaurantDto> findById(@PathVariable Long id);

    @Operation(
        summary = "Register a restaurant",
        description = "Register a restaurant.",
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(schema = @Schema(implementation = RestaurantDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<RestaurantDto> create(@RequestBody @Valid RestaurantInputDto restaurantInputDto);

    @Operation(
        summary = "Update a restaurant",
        description = "Update a restaurant.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = RestaurantDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<RestaurantDto> update(@PathVariable Long id,@RequestBody @Valid RestaurantInputDto restaurantInputDto);
    
    @Operation(
        summary = "Delete a restaurant",
        description = "Delete a restaurant.",
        responses = {
            @ApiResponse(description = "No Content",responseCode = "204", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id);

     @Operation(
        summary = "Find a restaurant by name and fee",
        description = "Finds a restaurant by their name and/or fee params.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = RestaurantDto.class))
            ),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<List<Restaurant>> findByNameAndFee(String name, BigDecimal minDeliveryFee, BigDecimal maxDeliveryFee);

     @Operation(
        summary = "Find a restaurant by name and zero delivery fee",
        description = "Find a restaurant by name and zero delivery fee.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = RestaurantDto.class))
            ),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<List<Restaurant>> findByZeroDeliveryFee(String name);

    @Operation(hidden = true)
    public ResponseEntity<Restaurant> findFirst();
    

    public ResponseEntity<Void> active(@PathVariable Long id);

    public ResponseEntity<Void> deactive(@PathVariable Long id);

    public ResponseEntity<Void> activations(@RequestBody List<Long> ids);

    public ResponseEntity<Void> deactivations(@RequestBody List<Long> ids);

         public ResponseEntity<Void> opening(@PathVariable Long id);

         public ResponseEntity<Void> closing(@PathVariable Long id);

}
