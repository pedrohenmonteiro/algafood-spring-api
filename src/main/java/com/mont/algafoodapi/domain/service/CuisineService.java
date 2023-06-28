package com.mont.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.repository.CuisineRepository;

@Service
public class CuisineService {
    
    @Autowired 
    private CuisineRepository cuisineRepository;

    public Cuisine save(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }
}
