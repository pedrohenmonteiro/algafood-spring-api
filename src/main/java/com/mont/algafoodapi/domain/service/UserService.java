package com.mont.algafoodapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.UserMapper;
import com.mont.algafoodapi.api.model.UserDto;
import com.mont.algafoodapi.api.model.input.UserInputDto;
import com.mont.algafoodapi.api.model.input.UserInputWithoutPasswordDto;
import com.mont.algafoodapi.domain.exception.BadRequestException;
import com.mont.algafoodapi.domain.exception.ConflictException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.User;
import com.mont.algafoodapi.domain.repository.UserRepository;

import jakarta.persistence.EntityManager;

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
        Optional<User> userExistent = userRepository.findByEmail(userInputDto.getEmail());
        if(userExistent.isPresent()) {
            throw new BadRequestException("Email already exists.");
        }

        return userMapper.fromEntityToDto(userRepository.save(user));
    }

    public UserDto update(Long id, UserInputWithoutPasswordDto userInputWithoutPasswordDto) {
        var user = getUser(id);
        
        if(!user.getEmail().equals(userInputWithoutPasswordDto.getEmail())) {
            if(userRepository.findByEmail(userInputWithoutPasswordDto.getEmail()).isPresent()) {
                throw new BadRequestException("Email already exists.");
            }}

        userMapper.copyToDomainObject(userInputWithoutPasswordDto, user);
        return userMapper.fromEntityToDto(userRepository.save(user));
    }
    
    public void delete(Long id) {
        try {
            getUser(id);
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Cannot delete resource due to existing references");
        }
    }

    private User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }

    
}