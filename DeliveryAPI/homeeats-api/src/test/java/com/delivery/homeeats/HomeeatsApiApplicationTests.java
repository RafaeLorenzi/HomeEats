package com.delivery.homeeats;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import javax.validation.ConstraintViolationException;

import org.apache.http.HttpStatus;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.AbstractBeansOfTypeDatabaseInitializerDetector;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.KitchenNotFoundException;
import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.model.service.KitchenRegistrationService;

import  io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class RegisterKitchenIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;
	
	@Autowired
	private KitchenRegistrationService kitchenRegistrationService;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/kitchen";
		
		flyway.migrate();
		
	}
	
	
	@Test
	public void mostReturnStatus200_WhenConsultKitchen () {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(org.springframework.http.HttpStatus.OK.value());
	}
	
	
	@Test
	public void mustReturnStatus201_WhenRegisterKitchen() {
		given()
			.body("{ \"name\": \"chinese\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(org.springframework.http.HttpStatus.CREATED.value());
	}

	
}
