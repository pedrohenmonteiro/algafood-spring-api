package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.CityDto;
import com.mont.algafoodapi.api.model.input.CityInputDto;
import com.mont.algafoodapi.domain.model.City;

@Component
public class CityMapper {
    
    private ModelMapper modelMapper;

    
    public City fromDtoToEntity(CityInputDto cityInput) {
        return modelMapper.map(cityInput, City.class);
    }

    public CityDto fromEntityToDto(City city) {
        return modelMapper.map(city, CityDto.class);
    }

    public List<CityDto> toCollectionDto(List<City> cities) {
        return cities.stream().map(this::fromEntityToDto).toList();
    }
} 
