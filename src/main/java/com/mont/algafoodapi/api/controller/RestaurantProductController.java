package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.ProductDto;
import com.mont.algafoodapi.api.model.input.ProductInputDto;
import com.mont.algafoodapi.domain.service.ProductService;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(productService.findAll(restaurantId));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long restaurantId, @PathVariable Long productId) {
        return ResponseEntity.ok(productService.findById(restaurantId, productId));
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@PathVariable Long restaurantId, @RequestBody ProductInputDto productInputDto) {
        return ResponseEntity.ok(productService.create(restaurantId, productInputDto));
    }
}
