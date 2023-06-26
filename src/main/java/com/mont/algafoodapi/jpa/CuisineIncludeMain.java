package com.mont.algafoodapi.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mont.algafoodapi.AlgafoodApiApplication;
import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.model.Cuisine;


public class CuisineIncludeMain {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        

            CuisineRepository cuisineRepository = applicationContext.getBean(CuisineRepository.class);
            
            Cuisine cuisine1 = new Cuisine(null, "Chinese");
            Cuisine cuisine2 = new Cuisine(null, "Australian");

            cuisine1 = cuisineRepository.save(cuisine1);
            cuisine2 = cuisineRepository.save(cuisine2);

            System.out.printf("%d - %s\n", cuisine1.getId(), cuisine1.getName());
            System.out.printf("%d - %s\n", cuisine2.getId(), cuisine2.getName());
            
        }
}
