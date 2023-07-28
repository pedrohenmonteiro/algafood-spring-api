package com.mont.algafoodapi.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mont.algafoodapi.api.model.PermissionDto;
import com.mont.algafoodapi.api.model.input.PermissionInputDto;
import com.mont.algafoodapi.domain.model.Permission;

@Component
public class PermissionMapper {
    
    @Autowired
    private ModelMapper modelMapper;

    public PermissionDto fromEntityToDto(Permission permission) {
        return modelMapper.map(permission, PermissionDto.class);
    }

    public Permission fromDtoToEntity(PermissionInputDto permissionInputDto) {
        return modelMapper.map(permissionInputDto, Permission.class);
    }

    public List<PermissionDto> fromCollectionToListDto(List<Permission> permissions) {
        return permissions.stream()
                .map(this::fromEntityToDto)
                .toList();
    }

    public void copyToDomainObject(PermissionInputDto permissionInputDto, Permission permission) {
        modelMapper.map(permissionInputDto, permission);
    }


}
