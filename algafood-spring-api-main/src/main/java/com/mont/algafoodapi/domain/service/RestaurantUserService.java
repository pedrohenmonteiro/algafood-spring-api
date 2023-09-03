package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.UserMapper;
import com.mont.algafoodapi.api.model.UserDto;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;

@Service
public class RestaurantUserService {
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    public List<UserDto> findAll(Long restaurantId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        return userMapper.toCollectionDto(restaurant.getUsers());
    }

    public void disassociateUser(Long restaurantId, Long userId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        var user = userService.getUser(userId);

        restaurant.removeUsers(user);
        restaurantRepository.save(restaurant);

    }

    public void associateUser(Long restaurantId, Long userId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        var user = userService.getUser(userId);

        restaurant.addUsers(user);
        restaurantRepository.save(restaurant);
    }
}
