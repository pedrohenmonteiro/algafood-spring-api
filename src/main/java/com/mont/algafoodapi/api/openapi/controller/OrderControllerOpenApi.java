package com.mont.algafoodapi.api.openapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mont.algafoodapi.api.model.OrderDto;
import com.mont.algafoodapi.api.model.OrderSummaryDto;
import com.mont.algafoodapi.api.model.input.OrderInputDto;
import com.mont.algafoodapi.api.openapi.model.OrderModelOpenApi;
import com.mont.algafoodapi.domain.filter.OrderFilter;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;

@Tag(name = "Orders", description = "Manage orders")
public interface OrderControllerOpenApi {
    
    @Operation(
        summary = "List all orders",
        description = "List all orders.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = OrderModelOpenApi.class)))
                }
            )
        }
    )
    public ResponseEntity<Page<OrderSummaryDto>> findAll(@PageableDefault(size = 10) @Nullable Pageable pageable, OrderFilter filter);


    @Operation(
        summary = "Find a order by code",
        description = "Finds a order by their Code.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = OrderDto.class))
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<OrderDto> findByCode(@PathVariable String orderCode);


    @Operation(
        summary = "Register a order",
        description = "Register a order.",
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(schema = @Schema(implementation = OrderDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<OrderDto> create(@RequestBody OrderInputDto orderInputDto);
}
