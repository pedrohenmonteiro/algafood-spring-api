package com.mont.algafoodapi.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.mont.algafoodapi.api.model.CityDto;
import com.mont.algafoodapi.api.model.input.CityInputDto;
import com.mont.algafoodapi.domain.exception.ExceptionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cities", description = "Manage cities")
public interface CityControllerOpenApi {
    

    @Operation(
        summary = "List all cities",
        description = "List all cities",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CityDto.class)))
                }
            ),
            @ApiResponse( description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    public ResponseEntity<List<CityDto>> findAll();

    @Operation(
        summary = "Find a city by id",
        description = "Finds a city by their Id.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = CityDto.class))
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<CityDto> findById(@Parameter(description = "The id of the city to find") @PathVariable Long id);

    @Operation(
        summary = "Register a city",
        description = "Register a city.",
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(schema = @Schema(implementation = CityDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(
                schema = @Schema(implementation = ExceptionResponse.class)
            )),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<CityDto> create(@RequestBody @Valid CityInputDto cityInputDto);

    @Operation(
        summary = "Update a city",
        description = "Update a city.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = CityDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content(
                schema = @Schema(implementation = ExceptionResponse.class)
            )),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<CityDto> update(@PathVariable Long id, @RequestBody @Valid CityInputDto cityInputDto);

    @Operation(
        summary = "Delete a city",
        description = "Delete a city.",
        responses = {
            @ApiResponse(
                description = "No Content",
                responseCode = "204",
                content = @Content
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id);
}
