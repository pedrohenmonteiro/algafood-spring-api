package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.UserDto;
import com.mont.algafoodapi.api.openapi.controller.RestaurantResponsibleControllerOpenApi;
import com.mont.algafoodapi.domain.service.RestaurantUserService;

@RestController
@RequestMapping(path = "/restaurants/{restaurantId}/responsibles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantResponsibleController implements RestaurantResponsibleControllerOpenApi {
    
    @Autowired
    private RestaurantUserService restaurantUserService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantUserService.findAll(restaurantId));
    }

    @PutMapping("/{responsibleId}")
    public ResponseEntity<Void> associateResponsible(@PathVariable Long restaurantId, @PathVariable Long responsibleId) {
        restaurantUserService.associateUser(restaurantId, responsibleId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{responsibleId}")
    public ResponseEntity<Void> disassociateResponsible(@PathVariable Long restaurantId, @PathVariable Long responsibleId) {
        restaurantUserService.disassociateUser(restaurantId, responsibleId);
        return ResponseEntity.noContent().build();
    }
}
