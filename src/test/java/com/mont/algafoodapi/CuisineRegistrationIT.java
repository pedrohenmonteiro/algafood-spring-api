package com.mont.algafoodapi;

import org.assertj.core.util.Arrays;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CuisineRegistrationIT {

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CuisineRepository cuisineRepository;

	@BeforeEach
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cuisine";
		databaseCleaner.clearTables();

		prepareData();
	}

	@Test
	void mustReturnStatus200_when_queryCuisines() {


		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());

}

	@Test
	void mustReturnStatus200AndRequestCorrectly_when_getCuisineById() {
		RestAssured.given()
			.pathParam("id", 2)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("name", Matchers.equalTo("Brazilian"));

	}

	@Test
	void mustReturnStatus404_when_getInexistentCuisine() {
		RestAssured.given()
			.pathParam("id", 10)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());

	}

	@Test
	void mustReturnStatusAndRequestCorrectly_when_getCuisineById() {
		RestAssured.given()
			.pathParam("id", 2)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("name", Matchers.equalTo("Brazilian"));

	}

	@Test
	void mustContain2Cuisines_when_getCuisines() {

		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("",Matchers.hasSize(2))
			.body("name", Matchers.hasItems("French", "Brazilian"));

}

	@Test
	void mustReturnStatus201_when_postCuisine() {

		RestAssured.given()
			.body("{ \"name\": \"French\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
}


	 void prepareData() {

		Cuisine cuisine1 = new Cuisine();
		Cuisine cuisine2 = new Cuisine();
		
		cuisine1.setName("French");
		cuisine2.setName("Brazilian");

		cuisineRepository.save(cuisine1);
		cuisineRepository.save(cuisine2);


	}

}



























	// @Autowired
	// 	private CuisineService cuisineService;

	// @Test
	// void mustAssignId_when_registrateCuisine() {
	// 	//scenary
	// 	Cuisine newCuisine = new Cuisine();
	// 	newCuisine.setName("Chinese");
	// 	//action
	// 	newCuisine = cuisineService.create(newCuisine);
		
	// 	//validation
	// 	assertNotNull(newCuisine);
	// 	assertNotNull(newCuisine.getId());

	// }

	// @Test
	// void mustFail_when_registrateCuisineWithoutName() {
	// 	Cuisine newCuisine = new Cuisine();
	// 	newCuisine.setName(null);
		
	// 	assertThrows(ConstraintViolationException.class, () -> {
	// 		cuisineService.create(newCuisine);
	// 	});
	// }

	// @Test
	// void mustFail_when_deleteCuisineAlreadyInUse() {
	// 	assertThrows(ConflictException.class, () -> {
	// 		cuisineService.delete(1L);
	// 	});
	// }

	// @Test
	// void mustFail_when_deleteInexistentCuisine() {

	// assertThrows(NotFoundException.class, () -> {
	// 		cuisineService.delete(100L);
	// 	});
	// }