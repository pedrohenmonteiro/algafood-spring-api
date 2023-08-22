package com.mont.algafoodapi.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.ProductPhotoDto;
import com.mont.algafoodapi.api.model.input.ProductPhotoInputDto;
import com.mont.algafoodapi.domain.service.CatalogProductPhotoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {
    
    @Autowired
    private CatalogProductPhotoService catalogProductPhotoService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductPhotoDto> updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId, @Valid ProductPhotoInputDto photoProductInput) throws IOException {

        return ResponseEntity.ok(catalogProductPhotoService.save(photoProductInput, restaurantId, productId));
    }
}
