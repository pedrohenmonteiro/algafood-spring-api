package com.mont.algafoodapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.service.CuisineService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
class CuisineRegistrationIntegrationTests {

	@Autowired
		private CuisineService cuisineService;

	@Test
	void cuisineRegistrationWithSuccess() {
		//scenary
		Cuisine newCuisine = new Cuisine();
		newCuisine.setName("Chinese");
		//action
		newCuisine = cuisineService.create(newCuisine);
		
		//validation
		assertNotNull(newCuisine);
		assertNotNull(newCuisine.getId());

	}

	@Test
	void mustFail_when_registrateCuisineWithoutName() {
		Cuisine newCuisine = new Cuisine();
		newCuisine.setName(null);
		
		assertThrows(ConstraintViolationException.class, () -> {
			cuisineService.create(newCuisine);
		});
	}

}
