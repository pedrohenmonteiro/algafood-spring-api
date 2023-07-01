package com.mont.algafoodapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.Cuisine;

public interface CuisineRepository extends JpaRepository<Cuisine, Long>{
    
    // @Query("from Cuisine WHERE name like %:name%")
    List<Cuisine> findByName(String name);

}
