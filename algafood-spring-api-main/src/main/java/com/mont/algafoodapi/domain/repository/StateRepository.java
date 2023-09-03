package com.mont.algafoodapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mont.algafoodapi.domain.model.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
