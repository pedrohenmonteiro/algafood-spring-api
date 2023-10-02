package com.mont.algafoodapi.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.PathVariable;

import com.mont.algafoodapi.api.mapper.ProductPhotoMapper;
import com.mont.algafoodapi.api.model.ProductPhotoDto;
import com.mont.algafoodapi.api.model.input.ProductPhotoInputDto;
import com.mont.algafoodapi.domain.exception.NotFoundException;
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


    public ProductPhotoDto findPhotoById(Long restaurantId, Long productId) {
        return productPhotoMapper.fromEntityToDto(findOrFail(restaurantId, productId));
    }


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


    public InputStreamResource servePhoto(@PathVariable Long restaurantId, @PathVariable Long productId, String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        ProductPhoto photo = findOrFail(restaurantId, productId);

        var mediaTypePhoto = MediaType.parseMediaType(photo.getContentType());
        List<MediaType> mediaTypesAcceptable = MediaType.parseMediaTypes(acceptHeader);

        verifyMediaTypeIsSupported(mediaTypePhoto, mediaTypesAcceptable);

        InputStream inputStream = photoStorageService.recover(photo.getFileName());

        return new InputStreamResource(inputStream);
        }


    public void delete(Long restaurantId, Long productId) {
        ProductPhoto photo = findOrFail(restaurantId, productId);

        productRepository.delete(photo);
        productRepository.flush();

        photoStorageService.remove(photo.getFileName());
    }

    public ProductPhoto findOrFail(Long restaurantId, Long productId) {
        return productRepository.findPhotoById(restaurantId, productId).orElseThrow(() -> new NotFoundException("Photo id " + productId + " not found"));
    }

    private void verifyMediaTypeIsSupported(MediaType mediaTypePhoto, List<MediaType> mediaTypesAcceptable) throws HttpMediaTypeNotAcceptableException {
        boolean isSupported = mediaTypesAcceptable.stream()
            .anyMatch(mediaTypeAcceptable -> mediaTypeAcceptable.isCompatibleWith(mediaTypePhoto));

            if(!isSupported) {
                throw new HttpMediaTypeNotAcceptableException(mediaTypesAcceptable);
            }
    }
}
