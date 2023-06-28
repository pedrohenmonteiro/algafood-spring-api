package com.mont.algafoodapi.jpa;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mont.algafoodapi.AlgafoodApiApplication;
import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.repository.CuisineRepository;

public class CuisineDeleteMain {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        

            CuisineRepository cuisineRepository = applicationContext.getBean(CuisineRepository.class);

            Cuisine cuisine1 = new Cuisine(1L, "Chinese");

            cuisineRepository.delete(cuisine1);
        
        }
}
