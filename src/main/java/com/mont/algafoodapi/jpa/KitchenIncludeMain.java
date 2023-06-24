package com.mont.algafoodapi.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.mont.algafoodapi.AlgafoodApiApplication;
import com.mont.algafoodapi.model.Kitchen;

import jakarta.transaction.Transactional;

public class KitchenIncludeMain {
    
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        

            KitchenRegistration kitchenRegistration = applicationContext.getBean(KitchenRegistration.class);
            
            Kitchen kitchen1 = new Kitchen(null, "Chinese");
            Kitchen kitchen2 = new Kitchen(null, "Australian");

            kitchen1 = kitchenRegistration.create(kitchen1);
            kitchen2 = kitchenRegistration.create(kitchen2);

            System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getName());
            System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getName());
            
        }
}
