package com.mont.algafoodapi.api.controller;

import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.input.PhotoProductInput;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {
    
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(@PathVariable Long restaurantId, Long productId, @Valid PhotoProductInput photoProductInput) {

        var fileName = UUID.randomUUID().toString() + "_" + photoProductInput.getFile().getOriginalFilename();

        var filePhoto = Path.of("/home/pedro/Desktop/catalog", fileName);

        System.out.println(photoProductInput.getDescription());
        System.out.println(filePhoto);
        System.out.println(photoProductInput.getFile().getContentType());

        try {
            photoProductInput.getFile().transferTo(filePhoto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
