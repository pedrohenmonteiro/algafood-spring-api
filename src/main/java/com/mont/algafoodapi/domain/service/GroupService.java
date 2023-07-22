package com.mont.algafoodapi.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.GroupMapper;
import com.mont.algafoodapi.api.model.GroupDto;
import com.mont.algafoodapi.domain.repository.GrouRepository;

@Service
public class GroupService {
    
    @Autowired
    private GrouRepository groupRepository;

    @Autowired
    private GroupMapper groupMapper;

    
    public List<GroupDto> findAll() {
        return groupMapper.toCollectionDto(groupRepository.findAll());
    }
}
