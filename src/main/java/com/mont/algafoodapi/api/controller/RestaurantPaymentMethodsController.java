package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.domain.service.RestaurantPaymentMethodsService;

@RestController
@RequestMapping("/restaurants/{id}/payment-methods")
public class RestaurantPaymentMethodsController {
    
    @Autowired
    private RestaurantPaymentMethodsService restaurantPaymentMethodsService;




      @GetMapping
    public ResponseEntity<List<PaymentMethodDto>> findAll(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantPaymentMethodsService.findAll(id));
    }
}
