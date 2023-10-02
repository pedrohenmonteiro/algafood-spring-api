package com.mont.algafoodapi.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.mont.algafoodapi.api.model.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Restaurants")
public interface RestaurantResponsibleControllerOpenApi {
    
    @Operation(
        summary = "List the users responsibles associated to restaurant",
        description = "List the users responsibles associated to restaurant.",
        responses = {
            @ApiResponse( description = "Success", responseCode = "200", content = @Content()),
            @ApiResponse(description = "Restaurant Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<List<UserDto>> findAll(@PathVariable Long restaurantId);

    @Operation(
        summary = "Association of restaurant with user responsible",
        description = "Associate a user responsible in restaurant",
        responses = {
            @ApiResponse(description = "Success", responseCode = "204", content = @Content),
            @ApiResponse(description = "Restaurant or User Not Found", responseCode = "404", content = @Content)
            
        }
    )
    @PutMapping("/{responsibleId}")
    public ResponseEntity<Void> associateResponsible(@PathVariable Long restaurantId, @PathVariable Long responsibleId);

    @Operation(
        summary = "Dissasociation of restaurant with user responsible",
        description = "Dissassociate a user responsible in restaurant",
        responses = {
            @ApiResponse(description = "Success", responseCode = "204", content = @Content),
            @ApiResponse(description = "Restaurant or User Not Found", responseCode = "404", content = @Content)
            
        }
    )
    @DeleteMapping("/{responsibleId}")
    public ResponseEntity<Void> disassociateResponsible(@PathVariable Long restaurantId, @PathVariable Long responsibleId);
}
