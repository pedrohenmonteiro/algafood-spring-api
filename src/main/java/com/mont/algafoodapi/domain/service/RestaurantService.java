package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired 
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
        
    }

    public Restaurant findById(Long id) {
        return getRestaurant(id);
    }
    
    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Long id, Restaurant restaurant) {
        getRestaurant(id);
        restaurant.setId(id);
        return restaurantRepository.save(restaurant);
    }

    public void delete(Long id) {
    getRestaurant(id);
     restaurantRepository.deleteById(id);
    }

    private Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }
}
