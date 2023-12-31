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

    public List<ProductDto> findAllActives(Long restaurantId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        return productMapper.toCollectionDto(productRepository.findActiveByRestaurant(restaurant));
    }

    public ProductDto findById(Long restaurantId, Long productId) {
        restaurantService.getRestaurant(restaurantId);
        return productMapper.fromEntityToDto(getProduct(restaurantId, productId));
    }

    public ProductDto create(Long restaurantId, ProductInputDto productInputDto) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        var product = productMapper.fromDtoToEntity(productInputDto);
        
        product.setRestaurant(restaurant);
        return productMapper.fromEntityToDto(productRepository.save(product));
    }

    public ProductDto update(Long restaurantId, Long productId, ProductInputDto productInputDto) {
       var product = getProduct(restaurantId, productId);
       productMapper.copyToDomainObject(productInputDto, product);
       return productMapper.fromEntityToDto(productRepository.save(product));
    }


    protected Product getProduct(Long restaurantId, Long productId) {
        return productRepository.findById(restaurantId, productId).orElseThrow(() -> new NotFoundException("Resource product id " + productId + " not found in restaurant id " + restaurantId));
    }
  
} 
