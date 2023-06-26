package com.mont.algafoodapi.jpa;


import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mont.algafoodapi.AlgafoodApiApplication;
import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.model.Cuisine;

public class CuisineQueryMain {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        

            CuisineRepository cuisineRepository = applicationContext.getBean(CuisineRepository.class);
            
            List<Cuisine> cuisines = cuisineRepository.findAll();

            for(Cuisine cuisine : cuisines) {
                System.out.println(cuisine.getName());
            }
        }
}
