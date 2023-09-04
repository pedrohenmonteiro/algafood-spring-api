package com.mont.algafoodapi.api.openapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mont.algafoodapi.api.model.CuisineDto;
import com.mont.algafoodapi.api.model.input.CuisineInputDto;
import com.mont.algafoodapi.api.openapi.model.CuisineModelOpenApi;
import com.mont.algafoodapi.domain.model.Cuisine;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cuisine", description = "Manage cuisine")
public interface CuisineControllerOpenApi {

    @Operation(
        summary = "List all cuisine",
        description = "List all cuisine",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CuisineModelOpenApi.class)))
                }
            ),
            @ApiResponse( description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    public ResponseEntity<Page<CuisineDto>> findAll(@PageableDefault(size = 10) Pageable pageable);

    @Operation(
        summary = "Find a cuisine by id",
        description = "Finds a cuisine by their Id.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = CuisineDto.class))
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<CuisineDto> findById(@PathVariable Long id);


    @Operation(
        summary = "Register a cuisine",
        description = "Register a cuisine.",
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(schema = @Schema(implementation = CuisineDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<CuisineDto> create(@RequestBody @Valid CuisineInputDto cuisineInputDto);

    @Operation(
        summary = "Update a city",
        description = "Update a city.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = CuisineDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<CuisineDto> update(@PathVariable Long id, @RequestBody @Valid CuisineInputDto cuisineInputDto);


    @Operation(
        summary = "Delete a cuisine",
        description = "Delete a cuisine.",
        responses = {
            @ApiResponse(description = "No Content",responseCode = "204", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id);

    @Operation(
        summary = "Find a cuisine by name",
        description = "Finds a cuisine by their name.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = CuisineDto.class))
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<List<Cuisine>> findByName(@RequestParam String name);
}
