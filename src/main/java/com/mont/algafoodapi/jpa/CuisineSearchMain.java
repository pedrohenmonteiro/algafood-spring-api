package com.mont.algafoodapi.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mont.algafoodapi.AlgafoodApiApplication;
import com.mont.algafoodapi.model.Cuisine;

public class CuisineSearchMain {
    public static void main(String[] args) {
          ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    
            CuisineRegistration cuisineRegistration = applicationContext.getBean(CuisineRegistration.class);
            Cuisine cuisine = cuisineRegistration.findById(1L);

            System.out.println(cuisine.getName());

        }
}
