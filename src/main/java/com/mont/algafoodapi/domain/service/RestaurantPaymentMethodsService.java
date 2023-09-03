package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.PaymentMethodMapper;
import com.mont.algafoodapi.api.model.PaymentMethodDto;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;

@Service
public class RestaurantPaymentMethodsService {
 
    @Autowired
    private PaymentMethodMapper paymentMethodMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired 
    private RestaurantService restaurantService;

    public List<PaymentMethodDto> findAll(Long id) {
        var restaurant = restaurantService.getRestaurant(id);
        return paymentMethodMapper.toCollectionDto(restaurant.getPaymentMethods());
    }

    public void disassociatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        var paymentMethod = paymentMethodService.getPaymentMethod(paymentMethodId);

        restaurant.removePaymentMethod(paymentMethod);
        restaurantRepository.save(restaurant);
    }

    public void associatePaymentMethod(Long restaurantId, Long paymentMethodId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        var paymentMethod = paymentMethodService.getPaymentMethod(paymentMethodId);

        restaurant.addPaymentMethod(paymentMethod);
        restaurantRepository.save(restaurant);
    }


    
}
