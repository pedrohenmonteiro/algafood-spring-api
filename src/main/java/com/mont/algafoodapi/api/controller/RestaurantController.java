package com.mont.algafoodapi.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.domain.service.RestaurantService;

@RestController
@RequestMapping
public class RestaurantController {
    
    @Autowired
    private RestaurantService restaurantService;
}
