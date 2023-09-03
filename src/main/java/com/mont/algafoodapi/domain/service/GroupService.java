package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.GroupMapper;
import com.mont.algafoodapi.api.model.GroupDto;
import com.mont.algafoodapi.api.model.input.GroupInputDto;
import com.mont.algafoodapi.domain.exception.ConflictException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Group;
import com.mont.algafoodapi.domain.repository.GroupRepository;

@Service
public class GroupService {
    
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    
    public List<GroupDto> findAll() {
        return groupMapper.toCollectionDto(groupRepository.findAll());
    }

    public GroupDto findById(Long id) {
        return groupMapper.fromEntityToDto(getGroup(id));
    }

    public GroupDto create(GroupInputDto groupInputDto) {
        var group = groupMapper.fromDtoToEntity(groupInputDto);
        return groupMapper.fromEntityToDto(groupRepository.save(group));
    }

    public GroupDto update(Long id, GroupInputDto groupInputDto) {
        var group = getGroup(id);
        groupMapper.copyToDomainObject(groupInputDto, group);
        return groupMapper.fromEntityToDto(groupRepository.save(group));

    }


    public void delete(Long id) {
        try {
        groupRepository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException("Cannot delete resource group id "+id+" due to existing references");
        }
    }

    protected Group getGroup(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource group id " + id + " not found"));
    }

}
 
   

