package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.UserDto;
import com.mont.algafoodapi.api.model.input.UserInputDto;
import com.mont.algafoodapi.api.model.input.UserInputWithoutPasswordDto;
import com.mont.algafoodapi.domain.model.User;

@Component
public class UserMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public UserDto fromEntityToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
    
    public User fromDtoToEntity(UserInputDto userInputDto) {
        return modelMapper.map(userInputDto, User.class);
    }


    public List<UserDto> toCollectionDto(List<User> user) {
      return user.stream().map(this::fromEntityToDto).toList();
    }

    public void copyToDomainObject(UserInputDto userInputDto, User user) {
        modelMapper.map(userInputDto, user);
    }

     public void copyToDomainObject(UserInputWithoutPasswordDto userInputDto, User user) {
        modelMapper.map(userInputDto, user);
    }
}
