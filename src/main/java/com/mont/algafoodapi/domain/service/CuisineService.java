package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.exception.BadRequestException;
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
        return cuisineRepository.findById(id);
    }

    public Cuisine create(Cuisine cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public Cuisine update(Long id, Cuisine cuisine) {
        cuisine.setId(id);
        return cuisineRepository.save(cuisine);
    }

    public void delete(Long id) {
        try {
        cuisineRepository.delete(id);
         } catch(DataIntegrityViolationException exception) {
            throw new BadRequestException("Can not remove resource in use");
        } catch(EmptyResultDataAccessException exception) {
            throw new NotFoundException("Resource not found");
        }
    }
} 
