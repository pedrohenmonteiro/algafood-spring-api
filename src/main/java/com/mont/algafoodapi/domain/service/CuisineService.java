package com.mont.algafoodapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mont.algafoodapi.api.mapper.CuisineMapper;
import com.mont.algafoodapi.api.model.CuisineDto;
import com.mont.algafoodapi.api.model.input.CuisineInputDto;
import com.mont.algafoodapi.domain.exception.ConflictException;
import com.mont.algafoodapi.domain.exception.NotFoundException;
import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.repository.CuisineRepository;

@Service
public class CuisineService {
    
    @Autowired 
    private CuisineRepository cuisineRepository;

    @Autowired
    private CuisineMapper cuisineMapper;

    public Page<CuisineDto> findAll(Pageable pageable) {
        var cuisinePage = cuisineRepository.findAll(pageable);
        var cuisineDto = cuisineMapper.toCollectionDto(cuisinePage.getContent());

        return new PageImpl<>(cuisineDto, pageable, cuisinePage.getTotalElements());
        
    }

    public CuisineDto findById(Long id) {
        return cuisineMapper.fromEntityToDto(getCuisine(id));
    }
    
    public CuisineDto create(CuisineInputDto cuisineInputDto) {
        var cuisine = cuisineMapper.fromDtoToEntity(cuisineInputDto);
        return cuisineMapper.fromEntityToDto(cuisineRepository.save(cuisine));
    }

    public CuisineDto update(Long id, CuisineInputDto cuisineInputDto) {
        var cuisine = getCuisine(id);
        cuisineMapper.copyToDomainObject(cuisineInputDto, cuisine);
        return cuisineMapper.fromEntityToDto(cuisineRepository.save(cuisine));
    }

    public void delete(Long id) {
        try {
            getCuisine(id);
            cuisineRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Cannot delete resource cuisine id "+id+" due to existing references");
        }
    }

    private Cuisine getCuisine(Long id) {
        return cuisineRepository.findById(id).orElseThrow(() -> new NotFoundException("Resource cuisine id " + id + " not found"));
    }
    
} 
