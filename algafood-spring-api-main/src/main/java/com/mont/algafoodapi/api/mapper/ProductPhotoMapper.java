package com.mont.algafoodapi.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.ProductPhotoDto;
import com.mont.algafoodapi.domain.model.ProductPhoto;

@Component
public class ProductPhotoMapper {
    
    @Autowired
    private ModelMapper modelMapper;
    
    public ProductPhotoDto fromEntityToDto(ProductPhoto productPhoto) {
        return modelMapper.map(productPhoto, ProductPhotoDto.class);
    }

}
