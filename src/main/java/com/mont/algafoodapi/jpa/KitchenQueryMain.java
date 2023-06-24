package com.mont.algafoodapi.jpa;


import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mont.algafoodapi.AlgafoodApiApplication;
import com.mont.algafoodapi.model.Kitchen;

public class KitchenQueryMain {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        

            KitchenRegistration kitchenRegistration = applicationContext.getBean(KitchenRegistration.class);
            
            List<Kitchen> kitchens = kitchenRegistration.list();

            for(Kitchen kitchen : kitchens) {
                System.out.println(kitchen.getName());
            }
        }
}
