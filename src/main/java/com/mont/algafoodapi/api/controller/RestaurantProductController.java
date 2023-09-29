package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.ProductDto;
import com.mont.algafoodapi.api.model.input.ProductInputDto;
import com.mont.algafoodapi.api.openapi.controller.RestaurantProductControllerOpenApi;
import com.mont.algafoodapi.core.security.CheckSecurity;
import com.mont.algafoodapi.domain.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/restaurants/{restaurantId}/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProductController implements RestaurantProductControllerOpenApi {

    @Autowired
    private ProductService productService;

    @CheckSecurity.Restaurants.allowQuery
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(@PathVariable Long restaurantId, @RequestParam(required = false) boolean includeInactive) {
        var response = ResponseEntity.ok(productService.findAllActives(restaurantId));
        if(includeInactive) {
            response = ResponseEntity.ok(productService.findAll(restaurantId)); 
        }
        return response;
    }

    @CheckSecurity.Restaurants.allowQuery
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        return ResponseEntity.ok(productService.findById(restaurantId, productId));
    }

    @CheckSecurity.Restaurants.allowEdit
    @PostMapping
    public ResponseEntity<ProductDto> create(@PathVariable Long restaurantId, @RequestBody @Valid ProductInputDto productInputDto) {
        return ResponseEntity.ok(productService.create(restaurantId, productInputDto));
    }

    @CheckSecurity.Restaurants.allowEdit
    @PutMapping("/{productId}")
     public ResponseEntity<ProductDto> update(@PathVariable Long restaurantId, @PathVariable Long productId, @RequestBody @Valid ProductInputDto productInputDto) {
        return ResponseEntity.ok(productService.update(restaurantId,productId, productInputDto));
    }
}
