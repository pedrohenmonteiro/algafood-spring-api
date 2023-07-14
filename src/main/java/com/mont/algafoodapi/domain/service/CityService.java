package com.mont.algafoodapi.domain.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.exception.ConflictException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.City;
import com.mont.algafoodapi.domain.repository.CityRepository;
import com.mont.algafoodapi.domain.repository.StateRepository;

@Service
public class CityService {
    
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Long id) {
        return getCity(id);
    }
    
    public City create(City city) {
        if(Objects.nonNull(city.getId())) {
            throw new BadRequestException("id must be null");
        }

        setState(city);
        return cityRepository.save(city);
        
    }

    public City update(Long id, City city) {
        getCity(id);
        city.setId(id);
        setState(city);
        return cityRepository.save(city);
    }

    public void delete(Long id) {
        try {
            getCity(id);
            cityRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Cannot delete resource due to existing references");
        }
}

    private City getCity(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new NotFoundException("Resource not found"));
    }

     private void setState(City city) {
        var stateId = city.getState().getId();
        var state = stateRepository.findById(stateId).orElseThrow(() -> new BadRequestException("Resource state id " + stateId + " not found"));
        city.setState(state); 
    }
}