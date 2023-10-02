package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.ProductDto;
import com.mont.algafoodapi.api.model.input.ProductInputDto;
import com.mont.algafoodapi.domain.model.Product;

@Component
public class ProductMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public ProductDto fromEntityToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    public Product fromDtoToEntity(ProductInputDto productInputDto) {
        return modelMapper.map(productInputDto, Product.class);
    }

    public List<ProductDto> toCollectionDto(List<Product> product) {
        return product.stream().map(this::fromEntityToDto).toList();
    }

    public void copyToDomainObject(ProductInputDto productInputDto, Product product) {
        modelMapper.map(productInputDto, product);
    }
}
