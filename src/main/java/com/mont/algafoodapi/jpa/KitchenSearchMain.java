package com.mont.algafoodapi.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mont.algafoodapi.AlgafoodApiApplication;
import com.mont.algafoodapi.model.Kitchen;

public class KitchenSearchMain {
    public static void main(String[] args) {
          ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    
            KitchenRegistration kitchenRegistration = applicationContext.getBean(KitchenRegistration.class);
            Kitchen kitchen = kitchenRegistration.findById(1L);

            System.out.println(kitchen.getName());

        }
}
