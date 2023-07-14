package com.mont.algafoodapi.domain.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.exception.ConflictException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired 
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
        
    }

    public Restaurant findById(Long id) {
        return getRestaurant(id);
    }
    
    public Restaurant create(Restaurant restaurant) {
        if(Objects.nonNull(restaurant.getId())) {
            throw new BadRequestException("id must be null");
        }

        setCuisine(restaurant);   
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Long id, Restaurant restaurant) {
        var entity = getRestaurant(id);
        restaurant.setId(id);
        restaurant.setPaymentMethods(entity.getPaymentMethods());
        restaurant.setAddress(entity.getAddress());
        restaurant.setDateCreation(entity.getDateCreation());
        restaurant.setDateLastUpdate(entity.getDateLastUpdate());
        restaurant.setAddress(entity.getAddress());
        setCuisine(restaurant);
        return restaurantRepository.save(restaurant);
    }

    public void delete(Long id) {
        try {
            getRestaurant(id);
            restaurantRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Cannot delete resource due to existing references");
        }
    }

    private Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }

    private void setCuisine(Restaurant restaurant) {
        var cuisineId = restaurant.getCuisine().getId();
        var cuisine = cuisineRepository.findById(cuisineId).orElseThrow(() -> new BadRequestException("Resource cuisine id " + cuisineId + " not found"));
        restaurant.setCuisine(cuisine);

    }
}
