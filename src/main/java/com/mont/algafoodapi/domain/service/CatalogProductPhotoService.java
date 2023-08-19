package com.mont.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.ProductPhotoMapper;
import com.mont.algafoodapi.api.model.ProductPhotoDto;
import com.mont.algafoodapi.api.model.input.ProductPhotoInputDto;
import com.mont.algafoodapi.domain.model.ProductPhoto;
import com.mont.algafoodapi.domain.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class CatalogProductPhotoService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPhotoMapper productPhotoMapper;

    @Transactional
    public ProductPhotoDto save(ProductPhotoInputDto photoProductInput, Long restaurantId, Long productId) {
        
        var product = productService.getProduct(restaurantId, productId);
        var existentPhoto = productRepository.findPhotoById(restaurantId, productId);

        if(existentPhoto.isPresent()) {
            productRepository.delete(existentPhoto.get());
        }

        ProductPhoto photo = new ProductPhoto();

        var file = photoProductInput.getFile();

        photo.setProduct(product);
        photo.setDescription(photoProductInput.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSize(file.getSize());
        photo.setFileName(file.getOriginalFilename());

        return productPhotoMapper.fromEntityToDto(productRepository.save(photo));
    }
}
