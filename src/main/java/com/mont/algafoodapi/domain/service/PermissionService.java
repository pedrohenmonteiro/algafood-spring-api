package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.PermissionMapper;
import com.mont.algafoodapi.api.model.PermissionDto;
import com.mont.algafoodapi.api.model.input.PermissionInputDto;
import com.mont.algafoodapi.domain.exception.ConflictException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Permission;
import com.mont.algafoodapi.domain.repository.PermissionRepository;

@Service
public class PermissionService {
    
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;

    public List<PermissionDto> findAll() {
        return permissionMapper.fromCollectionToListDto(permissionRepository.findAll());
    }

    public PermissionDto findById(Long id) {
        return permissionMapper.fromEntityToDto(getPermission(id));
    }

    public PermissionDto create(PermissionInputDto permissionInputDto) {
        var permission = permissionMapper.fromDtoToEntity(permissionInputDto);
        return permissionMapper.fromEntityToDto(permissionRepository.save(permission));
    }

    public PermissionDto update(Long id, PermissionInputDto permissionInputDto) {
        var permission = getPermission(id);
        permissionMapper.copyToDomainObject(permissionInputDto, permission);
        return permissionMapper.fromEntityToDto(permissionRepository.save(permission));
    }

    public void delete(Long id) {
        try {
            getPermission(id);
            permissionRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
          throw new ConflictException("Cannot delete resource permission id "+id+" due to existing references");

        }
    }

    protected Permission getPermission(Long id) {
        return permissionRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource permission id " + id + " not found"));
    }

    
}
