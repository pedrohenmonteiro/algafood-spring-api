package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.ProductMapper;
import com.mont.algafoodapi.api.model.ProductDto;
import com.mont.algafoodapi.api.model.input.ProductInputDto;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Product;
import com.mont.algafoodapi.domain.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ProductRepository productRepository;



    public List<ProductDto> findAll(Long restaurantId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        return productMapper.toCollectionDto(productRepository.findByRestaurant(restaurant));
    }

    public ProductDto findById(Long restaurantId, Long productId) {
        restaurantService.getRestaurant(restaurantId);
        return productMapper.fromEntityToDto(getProduct(restaurantId, productId));
    }

    public ProductDto create(ProductInputDto productInputDto) {
        var product = productMapper.fromDtoToEntity(productInputDto);
        return productMapper.fromEntityToDto(productRepository.save(product));
    }

    private Product getProduct(Long restaurantId, Long productId) {
        return productRepository.findById(restaurantId, productId).orElseThrow(() -> new NotFoundException("Resource product id " + productId + " not found"));
    }
  
} 
