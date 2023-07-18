package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.RestaurantDto;
import com.mont.algafoodapi.api.model.input.RestaurantInputDto;
import com.mont.algafoodapi.domain.model.Restaurant;

@Component
public class RestaurantMapper {

    @Autowired
    private ModelMapper modelMapper;

       public Restaurant fromDtoToEntity(RestaurantInputDto restaurantInputDto) {
        
        // var cuisine = Cuisine.builder()
        //         .id(restaurantInputDto.getCuisineId().getId())
        //     .build();

        // var restaurant = Restaurant.builder()
        //         .name(restaurantInputDto.getName())
        //         .deliveryFee(restaurantInputDto.getDeliveryFee())
        //         .cuisine(cuisine)
        //     .build();    


            
        return modelMapper.map(restaurantInputDto, Restaurant.class);
    }

    
    public RestaurantDto fromEntityToDto(Restaurant restaurant) {

        // var cuisineDto = CuisineDto.builder()
        //         .id(restaurant.getCuisine().getId())
        //         .name(restaurant.getCuisine().getName())
        //     .build();

        // var restaurantDto = RestaurantDto.builder()
        //         .id(restaurant.getId())
        //         .name(restaurant.getName())
        //         .deliveryFee(restaurant.getDeliveryFee())
        //         .cuisine(cuisineDto)
        //     .build();    

            return modelMapper.map(restaurant, RestaurantDto.class);
    }


    public List<RestaurantDto> toCollectionDto(List<Restaurant> restaurants) {
        return restaurants.stream().map(this::fromEntityToDto).toList();
    }

   
 


        
}
