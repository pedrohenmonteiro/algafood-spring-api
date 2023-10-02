package com.mont.algafoodapi.api.openapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Orders")
public interface StatusOrderControllerOpenApi {
    
    @Operation(
        summary = "Confirmation of order",
        description = "Confirm the order",
        responses = {
            @ApiResponse(
                description = "Order confirmed with success", responseCode = "204", content = @Content()
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        }
    )
    public ResponseEntity<Void> confirm(@PathVariable String orderCode);

    @Operation(
        summary = "Register order delivery",
        description = "Register that the order has been delivered",
        responses = {
            @ApiResponse(
                description = "Order delivered with success", responseCode = "204", content = @Content()
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        }
    )
    public ResponseEntity<Void> delivered(@PathVariable String orderCode);


    @Operation(
        summary = "Register order cancellation",
        description = "Cancel the order",
        responses = {
            @ApiResponse(
                description = "Order canceled with success", responseCode = "204", content = @Content()
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
        }
    )
    public ResponseEntity<Void> cancel(@PathVariable String orderCode);
}
