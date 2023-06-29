package com.mont.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.City;

public interface CityRepository extends JpaRepository<City, Long> {
   
}
