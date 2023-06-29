package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.exception.BadRequestException;
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
        var stateId = city.getState().getId();
        var state = stateRepository.findById(stateId).orElseThrow(() -> new BadRequestException("Resource state id " + stateId + " not found"));
        city.setState(state);

        return cityRepository.save(city);
    }

    public City update(Long id, City city) {
        city.setId(id);
        return cityRepository.save(city);
    }

    public void delete(Long id) {
     getCity(id);
     cityRepository.deleteById(id);
}

    private City getCity(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }
}