package com.mont.algafoodapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.service.CuisineService;

@SpringBootTest
class CuisineRegistrationIntegrationTests {

	@Autowired
		private CuisineService cuisineService;

	@Test
	void CuisineRegistrationWithSuccess() {
		//scenary
		Cuisine newCuisine = new Cuisine();
		newCuisine.setName("Chinese");
		//action
		newCuisine = cuisineService.create(newCuisine);
		
		//validation
		Assertions.assertNotNull(newCuisine);
		Assertions.assertNotNull(newCuisine.getId());

	}

}
