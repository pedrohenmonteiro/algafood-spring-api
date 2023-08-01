package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.UserDto;
import com.mont.algafoodapi.domain.service.RestaurantUserService;

@RestController
@RequestMapping("/restaurants/{restaurantId}/responsibles")
public class RestaurantResponsibleController {
    
    @Autowired
    private RestaurantUserService restaurantUserService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantUserService.findAll(restaurantId));
    }
}
