package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.UserMapper;
import com.mont.algafoodapi.api.model.UserDto;
import com.mont.algafoodapi.api.model.input.UserInputDto;
import com.mont.algafoodapi.api.model.input.UserInputWithoutPasswordDto;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.User;
import com.mont.algafoodapi.domain.repository.UserRepository;

@Service
public class UserService {
   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> findAll() {
        return userMapper.toCollectionDto(userRepository.findAll());
    }

    public UserDto findById(Long id) {
        return userMapper.fromEntityToDto(getUser(id));
    }

    public UserDto create(UserInputDto userInputDto) {
        var user = userMapper.fromDtoToEntity(userInputDto);
        return userMapper.fromEntityToDto(userRepository.save(user));
    }

    public UserDto update(Long id, UserInputWithoutPasswordDto userInputWithoutPasswordDto) {
        var user = getUser(id);
        userMapper.copyToDomainObject(userInputWithoutPasswordDto, user);
        return userMapper.fromEntityToDto(userRepository.save(user));
    }

    private User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }



}
