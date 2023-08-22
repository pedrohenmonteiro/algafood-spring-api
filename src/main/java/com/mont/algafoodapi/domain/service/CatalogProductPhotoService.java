package com.mont.algafoodapi.domain.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.ProductPhotoMapper;
import com.mont.algafoodapi.api.model.ProductPhotoDto;
import com.mont.algafoodapi.api.model.input.ProductPhotoInputDto;
import com.mont.algafoodapi.domain.model.ProductPhoto;
import com.mont.algafoodapi.domain.repository.ProductRepository;
import com.mont.algafoodapi.domain.service.PhotoStorageService.NewPhoto;

import jakarta.transaction.Transactional;

@Service
public class CatalogProductPhotoService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPhotoMapper productPhotoMapper;

    @Autowired 
    private PhotoStorageService photoStorageService;


    @Transactional
    public ProductPhotoDto save(ProductPhotoInputDto photoProductInput, Long restaurantId, Long productId) throws IOException {
        
        var product = productService.getProduct(restaurantId, productId);
        var existentPhoto = productRepository.findPhotoById(restaurantId, productId);
        var nameNewFile = photoStorageService.generateFileName(photoProductInput.getFile().getOriginalFilename());
        String fileExistentName = null;

        if(existentPhoto.isPresent()) {
            fileExistentName = existentPhoto.get().getFileName();
            productRepository.delete(existentPhoto.get());
        } 
        
        var file = photoProductInput.getFile(); 
        
        ProductPhoto photo = new ProductPhoto();
        
        

        photo.setProduct(product);
        photo.setDescription(photoProductInput.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setFileName(file.getOriginalFilename());
        photo.setFileName(nameNewFile);

        photo = productRepository.save(photo);
        productRepository.flush();

        
        NewPhoto newPhoto = NewPhoto.builder()
        .fileName(photo.getFileName())
        .inputStream(file.getInputStream())
        .build();
        
        photoStorageService.replace(fileExistentName, newPhoto);


        return productPhotoMapper.fromEntityToDto(photo);
    }
}
