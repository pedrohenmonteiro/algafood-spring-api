package com.mont.algafoodapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest
class CuisineRegistrationIT {

@Test
void mustReturnStatus200_when_queryCuisines() {
	RestAssured.given()
		.basePath("/cuisine")
		.port(8081)
		.accept(ContentType.JSON)
	.when()
		.get()
	.then()
		.statusCode(HttpStatus.OK.value());

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