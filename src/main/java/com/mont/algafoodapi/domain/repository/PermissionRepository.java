package com.mont.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    
}
