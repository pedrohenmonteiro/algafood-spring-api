package com.mont.algafoodapi;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CuisineRegistrationIT {

	@LocalServerPort
	private int port;

	@Autowired
	private Flyway flyway;

	@BeforeEach
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cuisine";

		flyway.migrate();
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
	void mustContain4Cuisines_when_getCuisines() {


		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("",Matchers.hasSize(4))
			.body("name", Matchers.hasItems("Argentine", "Brazilian"));

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