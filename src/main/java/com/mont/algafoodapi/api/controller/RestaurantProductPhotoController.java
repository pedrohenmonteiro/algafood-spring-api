package com.mont.algafoodapi.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.ProductPhotoDto;
import com.mont.algafoodapi.api.model.input.ProductPhotoInputDto;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.service.CatalogProductPhotoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( path = "/restaurants/{restaurantId}/products/{productId}/photo", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProductPhotoController {
    
    @Autowired
    private CatalogProductPhotoService catalogProductPhotoService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductPhotoDto> updatePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
     @Valid ProductPhotoInputDto photoProductInput) throws IOException {

        return ResponseEntity.ok(catalogProductPhotoService.save(photoProductInput, restaurantId, productId));
    }

    @GetMapping
    public ResponseEntity<ProductPhotoDto> findPhotoById(@PathVariable Long restaurantId, @PathVariable Long productId) throws IOException {
        return ResponseEntity.ok(catalogProductPhotoService.findPhotoById(restaurantId, productId));
    }

    @GetMapping(produces = MediaType.ALL_VALUE)
    public  ResponseEntity<InputStreamResource> servePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
    @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        try {
              return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(catalogProductPhotoService.servePhoto(restaurantId, productId, acceptHeader));
        } catch(NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long restaurantId, @PathVariable Long productId) {
        catalogProductPhotoService.delete(restaurantId, productId);
        return ResponseEntity.noContent().build();
    }


}
