package com.mont.algafoodapi.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.mont.algafoodapi.api.model.GroupDto;
import com.mont.algafoodapi.api.model.input.GroupInputDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Groups", description = "Manage user groups")
public interface GroupControllerOpenApi {
    
    @Operation(
        summary = "List all groups",
        description = "List all groups",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = GroupDto.class))
                )
            ),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    public ResponseEntity<List<GroupDto>> findAll();


    @Operation(
        summary = "Find a group by id",
        description = "Finds a group by their Id.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = GroupDto.class))
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<GroupDto> findById(@PathVariable Long id);

    @Operation(
        summary = "Register a group",
        description = "Register a group.",
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(schema = @Schema(implementation = GroupDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<GroupDto> create(@RequestBody GroupInputDto groupInputDto);

    @Operation(
        summary = "Update a group",
        description = "Update a group.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = GroupDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<GroupDto> update(@PathVariable Long id, @RequestBody GroupInputDto groupInputDto);
    

     @Operation(
        summary = "Delete a group",
        description = "Delete a group.",
        responses = {
            @ApiResponse(description = "No Content",responseCode = "204", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id);
}
