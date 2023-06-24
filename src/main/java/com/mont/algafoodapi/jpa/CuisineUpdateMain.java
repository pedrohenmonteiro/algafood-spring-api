package com.mont.algafoodapi.jpa;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mont.algafoodapi.AlgafoodApiApplication;
import com.mont.algafoodapi.model.Cuisine;

public class CuisineUpdateMain {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        

            CuisineRegistration cuisineRegistration = applicationContext.getBean(CuisineRegistration.class);

            Cuisine cuisine1 = new Cuisine(1L, "Chinese");

            cuisineRegistration.save(cuisine1);
            
        
            
        }
}
