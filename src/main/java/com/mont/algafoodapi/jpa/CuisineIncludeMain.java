package com.mont.algafoodapi.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mont.algafoodapi.AlgafoodApiApplication;
import com.mont.algafoodapi.model.Cuisine;

import jakarta.transaction.Transactional;

public class CuisineIncludeMain {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        

            CuisineRegistration cuisineRegistration = applicationContext.getBean(CuisineRegistration.class);
            
            Cuisine cuisine1 = new Cuisine(null, "Chinese");
            Cuisine cuisine2 = new Cuisine(null, "Australian");

            cuisine1 = cuisineRegistration.create(cuisine1);
            cuisine2 = cuisineRegistration.create(cuisine2);

            System.out.printf("%d - %s\n", cuisine1.getId(), cuisine1.getName());
            System.out.printf("%d - %s\n", cuisine2.getId(), cuisine2.getName());
            
        }
}
