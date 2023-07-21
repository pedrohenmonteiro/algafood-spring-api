package com.mont.algafoodapi.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.RestaurantDto;
import com.mont.algafoodapi.api.model.input.RestaurantInputDto;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;
import com.mont.algafoodapi.domain.service.RestaurantService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findAll());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> findById(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> create(@RequestBody @Valid RestaurantInputDto restaurantInputDto) {
        

        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.create(restaurantInputDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> update(@PathVariable Long id,@RequestBody @Valid RestaurantInputDto restaurantInputDto) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.update(id, restaurantInputDto));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        restaurantService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name-and-fee")
    public ResponseEntity<List<Restaurant>> findByNameAndFee(String name, BigDecimal minDeliveryFee, BigDecimal maxDeliveryFee) {

    return ResponseEntity.ok().body(restaurantRepository.findByNameAndFee(name, minDeliveryFee, maxDeliveryFee));
    }

    @GetMapping("/free-delivery-fee")
    public ResponseEntity<List<Restaurant>> findByZeroDeliveryFee(String name) {

        return ResponseEntity.ok(restaurantRepository.findByZeroDeliveryFee(name));
    }

    @GetMapping("/first")
    public ResponseEntity<Restaurant> findFirst() {
        return ResponseEntity.ok(restaurantRepository.findFirst().orElseThrow(() -> new NotFoundException("Resource not found")));
    }
    

    @PutMapping("/{id}/active")
    public ResponseEntity<Void> active(@PathVariable Long id) {
        restaurantService.active(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/active")
    public ResponseEntity<Void> deactive(@PathVariable Long id) {
        restaurantService.deactive(id);
        return ResponseEntity.noContent().build();
    }
    

}
