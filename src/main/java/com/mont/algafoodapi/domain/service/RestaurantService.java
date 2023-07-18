package com.mont.algafoodapi.domain.service;

import java.time.OffsetDateTime;
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
import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired 
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

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
        restaurant.setDateCreation(OffsetDateTime.now());
        return restaurantMapper.fromEntityToDto(restaurantRepository.save(restaurant));
    }

    public RestaurantDto update(Long id, RestaurantInputDto restaurantInputDto) {
        var entity = getRestaurant(id);
        // var restaurant = restaurantMapper.fromDtoToEntity(restaurantInputDto);
        // restaurant.setId(id);
        // restaurant.setPaymentMethods(entity.getPaymentMethods());
        // restaurant.setAddress(entity.getAddress());
        // restaurant.setDateCreation(entity.getDateCreation());
        // restaurant.setDateLastUpdate(entity.getDateLastUpdate());
        // restaurant.setAddress(entity.getAddress());
        // setCuisine(restaurant);
        restaurantMapper.copyToDomainModel(restaurantInputDto, entity);
        return restaurantMapper.fromEntityToDto(restaurantRepository.save(entity));
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
