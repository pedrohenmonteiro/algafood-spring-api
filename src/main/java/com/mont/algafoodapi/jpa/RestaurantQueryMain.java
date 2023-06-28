package com.mont.algafoodapi.jpa;


import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mont.algafoodapi.AlgafoodApiApplication;
import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;

public class RestaurantQueryMain {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        

            RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);
            
            List<Restaurant> restaurants = restaurantRepository.findAll();

            for(Restaurant restaurant : restaurants) {
                System.out.println(restaurant.getName() + " " + restaurant.getCuisine().getName());
            }
        }
}
