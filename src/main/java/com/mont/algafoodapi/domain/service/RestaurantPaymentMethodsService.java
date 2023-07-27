package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.PaymentMethodMapper;
import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;

@Service
public class RestaurantPaymentMethodsService {
 
    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<PaymentMethodDto> findAll(Long id) {
        var restaurant = getRestaurant(id);
        return paymentMethodMapper.toCollectionDto(restaurant.getPaymentMethods());
    }


     private Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }
}
