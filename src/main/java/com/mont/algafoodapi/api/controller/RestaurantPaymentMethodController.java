package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.api.openapi.controller.RestaurantPaymentMethodControllerOpenApi;
import com.mont.algafoodapi.domain.service.RestaurantPaymentMethodsService;

@RestController
@RequestMapping(path = "/restaurants/{restaurantId}/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantPaymentMethodController implements RestaurantPaymentMethodControllerOpenApi {
    
    @Autowired
    private RestaurantPaymentMethodsService restaurantPaymentMethodsService;


      @GetMapping
    public ResponseEntity<List<PaymentMethodDto>> findAll(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantPaymentMethodsService.findAll(restaurantId));
    }

     @DeleteMapping("/{paymentMethodId}")
     public ResponseEntity<Void> disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantPaymentMethodsService.disassociatePaymentMethod(restaurantId, paymentMethodId);
        return ResponseEntity.noContent().build();
     }

     @PutMapping("/{paymentMethodId}")
     public ResponseEntity<Void> associate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantPaymentMethodsService.associatePaymentMethod(restaurantId, paymentMethodId);
        return ResponseEntity.noContent().build();
     }

          
          

     
     
}
