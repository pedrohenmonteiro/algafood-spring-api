package com.mont.algafoodapi.api.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.mont.algafoodapi.api.model.PaymentMethodDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Restaurants")
public interface RestaurantPaymentMethodControllerOpenApi {
    
    @Operation(
        summary = "List the payment methods of a restaurant",
        description = "List the payment methods associated to a restaurant",
        responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Restaurant Not Found", responseCode = "404", content = @Content)
        }
    )
    public ResponseEntity<List<PaymentMethodDto>> findAll(@PathVariable Long restaurantId);

    @Operation(
        summary = "Disassociation of restaurant with payment method",
        description = "Disassociate a payment method of restaurant",
        responses = {
            @ApiResponse(description = "Success", responseCode = "204", content = @Content),
            @ApiResponse(description = "Restaurant or Payment Method Not Found", responseCode = "404", content = @Content)
            
        }
    )
    public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId);

    @Operation(
        summary = "Association of restaurant with payment method",
        description = "Associate a payment method of restaurant",
        responses = {
            @ApiResponse(description = "Success", responseCode = "204", content = @Content),
            @ApiResponse(description = "Restaurant or Payment Method Not Found", responseCode = "404", content = @Content)
            
        }
    )
    public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId);

}


