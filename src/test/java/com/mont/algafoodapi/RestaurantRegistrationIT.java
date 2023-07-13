package com.mont.algafoodapi;


import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.mont.algafoodapi.domain.model.Cuisine;
import com.mont.algafoodapi.domain.model.Restaurant;
import com.mont.algafoodapi.domain.repository.CuisineRepository;
import com.mont.algafoodapi.domain.repository.RestaurantRepository;
import com.mont.algafoodapi.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RestaurantRegistrationIT {
    
    @LocalServerPort
    private int port;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    private Restaurant pizzaRestaurant;

    private static final int INEXISTENT_RESTAURANT_ID = 100;

    @BeforeEach
    void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurants";

        databaseCleaner.clearTables();
        prepareData();
    }

    @Test
    void mustReturnStatus200AndRequestCorrectly_when_getRestaurantById() {
        RestAssured.given()
            .pathParam("id", pizzaRestaurant.getId())
            .accept(ContentType.JSON)
        .when()
            .get("/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("name", Matchers.equalTo(pizzaRestaurant.getName()));
    }

    @Test
    void mustReturnStatus404_when_getInexistentRestaurant() {
        RestAssured.given()
            .pathParam("id", INEXISTENT_RESTAURANT_ID)
            .accept(ContentType.JSON)
        .when()
            .get("/{id}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }



    void prepareData() {
        Cuisine cuisine1 = new Cuisine();
        cuisine1.setName("Italian");
        cuisineRepository.save(cuisine1);

        Cuisine cuisine2 = new Cuisine();
        cuisine2.setName("Brazilian");
        cuisineRepository.save(cuisine2);




        Restaurant restaurant1 = new Restaurant();
        restaurant1.setName("Pizza Borelo");
        restaurant1.setDeliveryFee(BigDecimal.ZERO);
        restaurant1.setCuisine(cuisine1);

        restaurantRepository.save(restaurant1);


        pizzaRestaurant = restaurant1;
    }
}
