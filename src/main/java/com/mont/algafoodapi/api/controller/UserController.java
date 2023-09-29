package com.mont.algafoodapi.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mont.algafoodapi.api.model.UserDto;
import com.mont.algafoodapi.api.model.input.PasswordInputDto;
import com.mont.algafoodapi.api.model.input.UserInputDto;
import com.mont.algafoodapi.api.model.input.UserInputWithoutPasswordDto;
import com.mont.algafoodapi.core.security.CheckSecurity;
import com.mont.algafoodapi.domain.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    
    @Autowired
    private UserService userService;

    @CheckSecurity.UserGroupsPermissions.allowsQuery
    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @CheckSecurity.UserGroupsPermissions.allowsQuery
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @CheckSecurity.UserGroupsPermissions.allowsEdit
    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserInputDto userInputDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userInputDto));
    }

    @CheckSecurity.UserGroupsPermissions.allowsChangeUser
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserInputWithoutPasswordDto userInputWithoutPasswordDto) {
        return ResponseEntity.ok(userService.update(id, userInputWithoutPasswordDto));
    }


    @CheckSecurity.UserGroupsPermissions.allowsChangeOwnPassword
    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid PasswordInputDto password) {
        userService.updatePassword(id, password.getCurrentPassword(), password.getNewPassword());
        return ResponseEntity.noContent().build();
    }

}
