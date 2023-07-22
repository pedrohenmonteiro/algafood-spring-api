package com.mont.algafoodapi.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

    //    var addressToAddressDtoTypeMap =  modelMapper.createTypeMap(Address.class, AddressDto.class);

    //    addressToAddressDtoTypeMap.<String>addMapping(
    //    src -> src.getCity().getState().getName()
    //    , (dest, value) -> dest.getCity().setStateName(value));

        return modelMapper;
    }
}
