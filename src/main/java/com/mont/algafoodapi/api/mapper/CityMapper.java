package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.CityDto;
import com.mont.algafoodapi.api.model.input.CityInputDto;
import com.mont.algafoodapi.domain.model.City;
import com.mont.algafoodapi.domain.model.State;

@Component
public class CityMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    
    public City fromDtoToEntity(CityInputDto cityInputDto) {
        return modelMapper.map(cityInputDto, City.class);
    }

    public CityDto fromEntityToDto(City city) {
        return modelMapper.map(city, CityDto.class);
    }

    public List<CityDto> toCollectionDto(List<City> cities) {
        return cities.stream().map(this::fromEntityToDto).toList();
    }

    public void copyToDomainObject(CityInputDto cityInputDto, City city) {
        // To avoid exception [org.springframework.orm.jpa.JpaSystemException:
        // identifier of an instance of com.mont.algafoodapi.domain.model.State was altered from 2 to 1]
        city.setState(new State());
        modelMapper.map(cityInputDto, city);
    }

} 
