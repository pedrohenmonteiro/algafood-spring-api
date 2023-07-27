package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.ProductMapper;
import com.mont.algafoodapi.api.model.ProductDto;

@Service
public class RestaurantProductService {
    
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RestaurantService restaurantService;

    public List<ProductDto> findAll(Long id) {
        var restaurant = restaurantService.getRestaurant(id);
        return productMapper.toCollectionDto(restaurant.getProducts());
    }
} 
