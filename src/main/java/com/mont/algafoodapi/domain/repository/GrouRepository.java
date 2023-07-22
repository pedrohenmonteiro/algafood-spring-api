package com.mont.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.Group;

public interface GrouRepository extends JpaRepository<Group, Long> {
    
}
