package com.mont.algafoodapi.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.CuisineDto;
import com.mont.algafoodapi.api.model.RestaurantDto;
import com.mont.algafoodapi.api.model.input.RestaurantInputDto;
import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.model.Restaurant;

@Component
public class RestaurantMapper {
    
    public RestaurantDto fromEntityToDto(Restaurant restaurant) {
        var cuisineDto = CuisineDto.builder()
                .id(restaurant.getCuisine().getId())
                .name(restaurant.getCuisine().getName())
            .build();

        var restaurantDto = RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .deliveryFee(restaurant.getDeliveryFee())
                .cuisine(cuisineDto)
            .build();    

        return restaurantDto;
    }

    public List<RestaurantDto> toCollectionDto(List<Restaurant> restaurants) {
        return restaurants.stream().map(this::fromEntityToDto).collect(Collectors.toList());
    }


    public Restaurant fromDtoToEntity(RestaurantInputDto dto) {
        
        var cuisine = Cuisine.builder()
                .id(dto.getCuisineId().getId())
            .build();

        var restaurant = Restaurant.builder()
                .name(dto.getName())
                .deliveryFee(dto.getDeliveryFee())
                .cuisine(cuisine)
            .build();    

        return restaurant;
    }



        
}
