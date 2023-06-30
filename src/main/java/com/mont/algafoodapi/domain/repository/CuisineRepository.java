package com.mont.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mont.algafoodapi.domain.model.Cuisine;

public interface CuisineRepository extends JpaRepository<Cuisine, Long>{
    
    @Query(value = "SELECT * FROM cuisine WHERE name = :name", nativeQuery = true)
    Cuisine findByName(@Param("name") String name);

}
