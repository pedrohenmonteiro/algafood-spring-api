package com.mont.algafoodapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mont.algafoodapi.api.model.AddressDto;
import com.mont.algafoodapi.api.model.input.OrderItemInputDto;
import com.mont.algafoodapi.domain.model.Address;
import com.mont.algafoodapi.domain.model.OrderItem;


@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

       var addressToAddressDtoTypeMap = modelMapper.createTypeMap(Address.class, AddressDto.class);

       addressToAddressDtoTypeMap.<String>addMapping(
       src -> src.getCity().getState().getName(), (dest, value) -> dest.getCity().setState(value));

       modelMapper.createTypeMap(OrderItemInputDto.class, OrderItem.class)
       .addMappings(mapper -> mapper.skip(OrderItem::setId));  
   
        
    
    return modelMapper;
    }
}
