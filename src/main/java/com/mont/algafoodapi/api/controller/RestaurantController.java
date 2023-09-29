package com.mont.algafoodapi.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.mont.algafoodapi.api.model.RestaurantDto;
import com.mont.algafoodapi.api.model.input.RestaurantInputDto;
import com.mont.algafoodapi.api.model.view.RestaurantView;
import com.mont.algafoodapi.api.openapi.controller.RestaurantControllerOpenApi;
import com.mont.algafoodapi.core.security.CheckSecurity;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;
import com.mont.algafoodapi.domain.service.RestaurantService;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController implements RestaurantControllerOpenApi{
    
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @CheckSecurity.Restaurants.allowQuery
    @JsonView(RestaurantView.Summary.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findAll());
    }

    @CheckSecurity.Restaurants.allowQuery
    @JsonView(RestaurantView.OnlyName.class)
    @GetMapping(params = "proj=name")
    public ResponseEntity<List<RestaurantDto>> findAllName() {
        return findAll();
    }

    @CheckSecurity.Restaurants.allowQuery    
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> findById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findById(id));
    }

    @CheckSecurity.Restaurants.allowEdit
    @PostMapping
    public ResponseEntity<RestaurantDto> create(@RequestBody @Valid RestaurantInputDto restaurantInputDto) {
        

        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.create(restaurantInputDto));
    }

    @CheckSecurity.Restaurants.allowEdit    
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> update(@PathVariable Long id,@RequestBody @Valid RestaurantInputDto restaurantInputDto) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.update(id, restaurantInputDto));
    }

    @CheckSecurity.Restaurants.allowEdit    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.allowQuery      
    @GetMapping("/by-name-and-fee")
    public ResponseEntity<List<Restaurant>> findByNameAndFee(
        @Nullable String name,
        @Nullable BigDecimal minDeliveryFee,
        @Nullable BigDecimal maxDeliveryFee) {

    return ResponseEntity.ok().body(restaurantRepository.findByNameAndFee(name, minDeliveryFee, maxDeliveryFee));
    }

    @CheckSecurity.Restaurants.allowQuery  
    @GetMapping("/free-delivery-fee")
    public ResponseEntity<List<Restaurant>> findByZeroDeliveryFee(String name) {

        return ResponseEntity.ok(restaurantRepository.findByZeroDeliveryFee(name));
    }

    @CheckSecurity.Restaurants.allowQuery      
    @GetMapping("/first")
    public ResponseEntity<Restaurant> findFirst() {
        return ResponseEntity.ok(restaurantRepository.findFirst().orElseThrow(() -> new NotFoundException("Resource not found")));
    }

    @CheckSecurity.Restaurants.allowEdit  
    @PutMapping("/{id}/active")
    public ResponseEntity<Void> active(@PathVariable Long id) {
        restaurantService.active(id);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.allowEdit  
    @DeleteMapping("/{id}/active")
    public ResponseEntity<Void> deactive(@PathVariable Long id) {
        restaurantService.deactive(id);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.allowEdit
    @PutMapping("/activations")
    public ResponseEntity<Void> activations(@RequestBody List<Long> ids) {
        restaurantService.active(ids);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.allowEdit  
    @DeleteMapping("/activations")
    public ResponseEntity<Void> deactivations(@RequestBody List<Long> ids) {
        restaurantService.deactive(ids);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.allowEdit  
    @PutMapping("/{id}/opening") 
         public ResponseEntity<Void> opening(@PathVariable Long id) {
        restaurantService.openRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.allowEdit
    @PutMapping("/{id}/closing") 
         public ResponseEntity<Void> closing(@PathVariable Long id) {
        restaurantService.closeRestaurant(id);
        return ResponseEntity.noContent().build();
    }


}
