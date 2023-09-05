package com.mont.algafoodapi.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.ServletWebRequest;

import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.api.model.input.PaymentMethodInputDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Payment Method", description = "Manage payment methods")
public interface PaymentMethodControllerOpenApi {
    
    @Operation(
        summary = "List all payment methods",
        description = "List all payment methods.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = PaymentMethodDto.class)))
                }
            ),
            @ApiResponse( description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    public ResponseEntity<List<PaymentMethodDto>> findAll(ServletWebRequest request);

    @Operation(
        summary = "Find a payment method by id",
        description = "Finds a payment method by their Id.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = PaymentMethodDto.class))
            ),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<PaymentMethodDto> findById(@PathVariable Long id, ServletWebRequest request);

    @Operation(
        summary = "Register a payment method",
        description = "Register a payment method.",
        responses = {
            @ApiResponse(
                description = "Created",
                responseCode = "201",
                content = @Content(schema = @Schema(implementation = PaymentMethodDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<PaymentMethodDto> create(@RequestBody @Valid PaymentMethodInputDto paymentMethodInputDto);


    @Operation(
        summary = "Update a city",
        description = "Update a city.",
        responses = {
            @ApiResponse(
                description = "Success",
                responseCode = "200",
                content = @Content(schema = @Schema(implementation = PaymentMethodDto.class))
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<PaymentMethodDto> update(@PathVariable Long id, @RequestBody @Valid PaymentMethodInputDto paymentMethodInputDto);

    @Operation(
        summary = "Delete a payment method",
        description = "Delete a payment method.",
        responses = {
            @ApiResponse(description = "No Content",responseCode = "204", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
        }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id);
}
