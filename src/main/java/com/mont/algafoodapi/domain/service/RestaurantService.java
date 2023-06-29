package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.exception.BadRequestException;
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
        return restaurantRepository.findById(id);
    }

    public Restaurant create(Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        var cuisine = cuisineRepository.findById(cuisineId);
        if(cuisine == null) {
            throw new BadRequestException("Resource cuisine id " + cuisineId + " not found"); 
        }
        restaurant.setCuisine(cuisine);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Long id, Restaurant restaurant) {
        restaurant.setId(id);
        return restaurantRepository.save(restaurant);
    }

    public void delete(Long id) {
        try {
        restaurantRepository.delete(id);
         } catch(DataIntegrityViolationException exception) {
            throw new BadRequestException("Can not remove resource in use");
        } catch(EmptyResultDataAccessException exception) {
            throw new NotFoundException("Resource not found");
        }
    }

}
