package com.mont.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.Cuisine;

public interface CuisineRepository extends JpaRepository<Cuisine, Long>{
    

}
