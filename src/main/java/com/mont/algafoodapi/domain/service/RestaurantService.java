package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.RestaurantMapper;
import com.mont.algafoodapi.api.model.RestaurantDto;
import com.mont.algafoodapi.api.model.input.RestaurantInputDto;
import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.exception.ConflictException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.CityRepository;
import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;

import jakarta.transaction.Transactional;

@Service
public class RestaurantService {

    @Autowired 
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;


    public List<RestaurantDto> findAll() {
        return restaurantMapper.toCollectionDto(restaurantRepository.findAll());
        
    }


    public RestaurantDto findById(Long id) {
        return restaurantMapper.fromEntityToDto(getRestaurant(id));
    }
    
    public RestaurantDto create(RestaurantInputDto restaurantInputDto) {
        
        var restaurant = restaurantMapper.fromDtoToEntity(restaurantInputDto);
        setCuisine(restaurant);
        setCity(restaurant);
        return restaurantMapper.fromEntityToDto(restaurantRepository.save(restaurant));
    }

    public RestaurantDto update(Long id, RestaurantInputDto restaurantInputDto) {
        var restaurant = getRestaurant(id);
        restaurantMapper.copyToDomainObject(restaurantInputDto, restaurant);   
        setCuisine(restaurant);
        setCity(restaurant);

        return restaurantMapper.fromEntityToDto(restaurantRepository.save(restaurant));
    }

    public void delete(Long id) {
        try {
            getRestaurant(id);
            restaurantRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Cannot delete resource restaurant id "+id+" due to existing references");
        }
    }

    @Transactional
    public void active(Long id) {
        var restaurant = getRestaurant(id);
        restaurant.active();
    }

    @Transactional
     public void deactive(Long id) {
        var restaurant = getRestaurant(id);
        restaurant.setActive(false);
    }

    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource restaurant id " + id + " not found"));
    }

    private void setCuisine(Restaurant restaurant) {
        var cuisineId = restaurant.getCuisine().getId();
        var cuisine = cuisineRepository.findById(cuisineId).orElseThrow(() -> new BadRequestException("Resource cuisine id " + cuisineId + " not found"));
        restaurant.setCuisine(cuisine);
    }

    private void setCity(Restaurant restaurant) {
        var cityId = restaurant.getAddress().getCity().getId();
        var city = cityRepository.findById(cityId).orElseThrow(() -> new BadRequestException("Resource city id " + cityId + " not found"));
        restaurant.getAddress().setCity(city);
    }
}
