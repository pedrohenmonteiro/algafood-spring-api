package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.model.Cuisine;

@RestController
@RequestMapping("/cuisine")
public class CuisineController {
    
    @Autowired
    private CuisineRepository cuisineRepository;

    @GetMapping
    public ResponseEntity<List<Cuisine>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(cuisineRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuisine> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(cuisineRepository.findById(id));
    }

    @PostMapping
    public ResponseEntity<Cuisine> create(@RequestBody Cuisine cuisine) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuisineRepository.save(cuisine));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuisine> update(@PathVariable Long id, @RequestBody Cuisine cuisine) {
        cuisine.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cuisineRepository.save(cuisine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var entity = cuisineRepository.findById(id);
        cuisineRepository.delete(entity);

        return ResponseEntity.noContent().build();
    }
}
