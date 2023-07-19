package com.mont.algafoodapi.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.input.CityInputDto;
import com.mont.algafoodapi.domain.model.City;

@Component
public class CityMapper {
    
    private ModelMapper modelMapper;

    
    public City fromDtoToEntity(CityInputDto cityInput) {
        return modelMapper.map(cityInput, City.class);
    }

    

}
