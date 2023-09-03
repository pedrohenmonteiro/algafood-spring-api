package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.CuisineDto;
import com.mont.algafoodapi.api.model.input.CuisineInputDto;
import com.mont.algafoodapi.domain.model.Cuisine;

@Component
public class CuisineMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public Cuisine fromDtoToEntity(CuisineInputDto cuisineInputDto) {
        return modelMapper.map(cuisineInputDto, Cuisine.class);
    }

    public CuisineDto fromEntityToDto(Cuisine cuisine) {
        return modelMapper.map(cuisine, CuisineDto.class);
    }

    public List<CuisineDto> toCollectionDto(List<Cuisine> cuisine) {
        return cuisine.stream().map(this::fromEntityToDto).toList();
    }

    public void copyToDomainObject(CuisineInputDto cuisineInputDto, Cuisine cuisine) {
       modelMapper.map(cuisineInputDto, cuisine);
    }

}
