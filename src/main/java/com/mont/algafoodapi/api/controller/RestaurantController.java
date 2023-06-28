package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.domain.service.RestaurantService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findAll());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Long cuisineId = restaurant.getCuisine().getId();
        var cuisine = cuisineRepository.findById(cuisineId);
        if(cuisine == null) {
            throw new BadRequestException("Resource cuisine id " + cuisineId + " not found"); 
        }
        restaurant.setCuisine(cuisine);

        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.create(restaurant));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> update(@PathVariable Long id,@RequestBody Restaurant restaurant) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.update(id, restaurant));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
