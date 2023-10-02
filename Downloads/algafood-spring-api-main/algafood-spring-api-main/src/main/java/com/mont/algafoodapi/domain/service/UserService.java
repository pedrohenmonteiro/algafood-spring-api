package com.mont.algafoodapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

@Service
public class UserService {
   
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDto> findAll() {
        return userMapper.toCollectionDto(userRepository.findAll());
    }


    public UserDto findById(Long id) {
        return userMapper.fromEntityToDto(getUser(id));
    }

    public UserDto create(UserInputDto userInputDto) {
        Optional<User> userExistent = userRepository.findByEmail(userInputDto.getEmail());
        if(userExistent.isPresent()) {
            throw new BadRequestException("Email already exists.");
        }
        
        var user = userMapper.fromDtoToEntity(userInputDto);
        user.setPassword(passwordEncoder.encode(userInputDto.getPassword()));
        
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
            throw new ConflictException("Cannot delete resource user id "+id+" due to existing references");
        }
    }


    public void updatePassword(Long id, String currentPassword, String newPassword) {
        var user = getUser(id);

        if(!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new BadRequestException("Current password is invalid");
        }
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }

    protected User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource user id " + id + " not found"));
    }

    
}