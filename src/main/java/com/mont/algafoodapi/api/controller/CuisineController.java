package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Cuisine> findAll() {
        return cuisineRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cuisine findById(@PathVariable Long id) {
        return cuisineRepository.findById(id);
    }
}
