package com.mont.algafoodapi.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mont.algafoodapi.api.model.StateDto;
import com.mont.algafoodapi.api.model.input.StateInputDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "States", description = "Manage states")
public interface StateControllerOpenApi {
    
    @Operation(
        summary = "List all states",
        description = "List all states.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = StateDto.class))
                )
            ),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<List<StateDto>> findAll();

    @Operation(
        summary = "Find a state by id",
        description = "Find a state by their Id.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200", 
                content = @Content(schema = @Schema(implementation = StateDto.class))
                ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<StateDto> findById(@PathVariable Long id);

    @Operation(
        summary = "Register a state",
        description = "Register a state.",
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(schema = @Schema(implementation = StateDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<StateDto> create(@RequestBody @Valid StateInputDto state);

    @Operation(
        summary = "Update a state",
        description = "Update a state.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = StateDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<StateDto> update(@PathVariable Long id, @RequestBody @Valid StateInputDto stateInputDto);

    @Operation(
        summary = "Delete a state",
        description = "Delete a state.",
        responses = {
            @ApiResponse(description = "No Content",responseCode = "204", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id);
}
