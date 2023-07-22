package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.GroupMapper;
import com.mont.algafoodapi.api.model.GroupDto;
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


    private Group getGroup(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource not found"));
    }
}
