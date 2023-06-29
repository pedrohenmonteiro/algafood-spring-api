package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.domain.model.City;
import com.mont.algafoodapi.domain.repository.CityRepository;

@Service
public class CityService {
    
    @Autowired
    private CityRepository cityRepository;

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public City findById(Long id) {
        return cityRepository.findById(id);
    }

    public City create(City city) {
        return cityRepository.save(city);
    }

    public City update(Long id, City city) {
        city.setId(id);
        return cityRepository.save(city);
    }

    public void delete(Long id) {
    cityRepository.delete(id);
    }
}
