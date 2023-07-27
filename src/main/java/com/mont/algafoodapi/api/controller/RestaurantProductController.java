package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.ProductDto;
import com.mont.algafoodapi.domain.service.RestaurantProductService;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    @Autowired
    private RestaurantProductService restaurantProductService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantProductService.findAll(restaurantId));
    }
}
