package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.exception.ConflictException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.repository.CuisineRepository;

@Service
public class CuisineService {
    
    @Autowired 
    private CuisineRepository cuisineRepository;

    public List<Cuisine> findAll() {
        return cuisineRepository.findAll();
        
    }

    public Cuisine findById(Long id) {
        return getCuisine(id);
    }
    
    public Cuisine create(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public Cuisine update(Long id, Cuisine cuisine) {
        var entity = getCuisine(id);
        cuisine.setId(id);
        return cuisineRepository.save(cuisine);
    }

    public void delete(Long id) {
        try {
            getCuisine(id);
            cuisineRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Cannot delete resource due to existing references");
        }
    }

    private Cuisine getCuisine(Long id) {
        return cuisineRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }
    
} 
