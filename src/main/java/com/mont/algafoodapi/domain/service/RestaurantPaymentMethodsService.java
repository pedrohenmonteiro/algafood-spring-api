package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.PaymentMethodMapper;
import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.PaymentMethod;
import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.PaymentMethodRepository;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;

@Service
public class RestaurantPaymentMethodsService {
 
    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethodDto> findAll(Long id) {
        var restaurant = getRestaurant(id);
        return paymentMethodMapper.toCollectionDto(restaurant.getPaymentMethods());
    }

    public void disassociatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        var restaurant = getRestaurant(restaurantId);
        var paymentMethod = getPaymentMethod(paymentMethodId);

        restaurant.removePaymentMethod(paymentMethod);
        restaurantRepository.save(restaurant);
    }

    public void associatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        var restaurant = getRestaurant(restaurantId);
        var paymentMethod = getPaymentMethod(paymentMethodId);

        restaurant.addPaymentMethod(paymentMethod);
        restaurantRepository.save(restaurant);
    }


     private Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }

    private PaymentMethod getPaymentMethod(Long id) {
        return paymentMethodRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }
}
