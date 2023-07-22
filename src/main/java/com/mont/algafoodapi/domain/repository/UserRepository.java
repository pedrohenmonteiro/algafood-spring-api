package com.mont.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
