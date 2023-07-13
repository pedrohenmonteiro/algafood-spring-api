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
import com.mont.algafoodapi.util.ResourceUtils;

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

    private static final int INEXISTENT_RESTAURANT_ID = 100;
    private int restaurantSize;
    private Restaurant pizzaRestaurant;
    private String incorrectJsonRestaurantWithoutFee;
    private String correctJsonBrazilianRestaurant;





    @BeforeEach
    void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurants";
        correctJsonBrazilianRestaurant = ResourceUtils.getContentFromResource("/json/correct/a-brasileira-restaurant.json");
        incorrectJsonRestaurantWithoutFee = ResourceUtils.getContentFromResource("/json/incorrect/restaurant-without-fee.json");


        databaseCleaner.clearTables();
        prepareData();
    }

      @Test
    void mustReturnStatus200AndCorrectSize_when_getRestaurant() {
        RestAssured.given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("", Matchers.hasSize(restaurantSize));
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


    @Test
    void mustReturnStatus201_when_postRestaurant() {
        RestAssured.given()
            .body(correctJsonBrazilianRestaurant)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
            
    }

    @Test
    void mustReturnStatus400_when_postRestaurantWithoutFee() {
        RestAssured.given()
            .body(incorrectJsonRestaurantWithoutFee)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value());
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

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setName("Feijoada Brasil");
        restaurant2.setDeliveryFee(BigDecimal.ZERO);
        restaurant2.setCuisine(cuisine2);
        restaurantRepository.save(restaurant2);



        pizzaRestaurant = restaurant1;
        restaurantSize = (int) restaurantRepository.count();
    }
}
