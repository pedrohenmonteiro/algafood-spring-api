package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.CityMapper;
import com.mont.algafoodapi.api.model.CityDto;
import com.mont.algafoodapi.api.model.input.CityInputDto;
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

    @Autowired
    private CityMapper cityMapper;

    public List<CityDto> findAll() {
        return cityMapper.toCollectionDto(cityRepository.findAll());
    }

    public CityDto findById(Long id) {
        return cityMapper.fromEntityToDto(getCity(id));
    }
    
    
    public CityDto create(CityInputDto cityInputDto) {

        var city = cityMapper.fromDtoToEntity(cityInputDto);
        setState(city);
        return cityMapper.fromEntityToDto(cityRepository.save(city));
        
    }

    public CityDto update(Long id, CityInputDto cityInputDto) {
        var city = getCity(id);
        cityMapper.copyToDomainObject(cityInputDto, city);
        setState(city);
        return cityMapper.fromEntityToDto(cityRepository.save(city));
    }

    public void delete(Long id) {
        try {
            getCity(id);
            cityRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Cannot delete resource city id "+id+" due to existing references");
        }
}

    protected City getCity(Long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new NotFoundException("Resource city " + cityId + " not found"));
    }

     private void setState(City city) {
        var stateId = city.getState().getId();
        var state = stateRepository.findById(stateId).orElseThrow(() -> new BadRequestException("Resource state id " + stateId + " not found"));
        city.setState(state); 
    }
}