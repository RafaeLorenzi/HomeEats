package com.delivery.homeeats;

import static io.restassured.RestAssured.given;

import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.CoreMatchers.equalToObject;
import static org.hamcrest.Matchers.hasSize;

import javax.validation.ConstraintViolationException;

import org.apache.http.HttpStatus;
import org.assertj.core.error.ShouldHaveSameSizeAs;
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
import com.delivery.homeeats.domain.service.KitchenRegistrationService;
import com.delivery.homeeats.domain.repository.KitchenRepository;
import com.delivery.homeeats.util.DatabaseCleaner;
import com.delivery.homeeats.util.ResourceUtils;

import groovyjarjarantlr4.v4.parse.ANTLRParser.finallyClause_return;
import  io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class RegisterKitchenIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private KitchenRegistrationService kitchenRegistrationService;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	private static final int NOT_EXISTE_KITCHENID = 100;
	
	private Kitchen fastFoodKitchen;
	private int amountOfRegisterKitchen;
	private String jsonCorrectChineseKitchen;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/kitchens";
		
		jsonCorrectChineseKitchen = ResourceUtils.getContentFromResource(
				"/json/correct/chinese-kitchen.json");
		
		
		
		databaseCleaner.clearTables();
		dataConstructor();

	}
	
	private void dataConstructor() {
		Kitchen kitchen1 = new Kitchen();
		kitchen1.setName("Thai");
		kitchenRepository.save(kitchen1);
		
		Kitchen kitchen2 = new Kitchen();
		kitchen2.setName("FastFood");
		kitchenRepository.save(kitchen2);
		
		amountOfRegisterKitchen = (int) kitchenRepository.count();
		
	}
	
	
	@Test
	public void mostReturnCorrectResponseStatus_WhenConsultExistentKitchen() {
		given()
		.pathParam("kitchenId", fastFoodKitchen.getId())
		.accept(ContentType.JSON)
	.when()
		.get("/{kitchenId}")
	.then()
		.statusCode(org.springframework.http.HttpStatus.OK.value())
		.body("name", equalToObject(fastFoodKitchen.getName()));
	}
	
	@Test
	public void mostReturnStatus404_WhenConsultNotExistKitchen() {
		given()
		.pathParam("kitchenId", NOT_EXISTE_KITCHENID)
		.accept(ContentType.JSON)
	.when()
		.get("/{kitchenId}")
	.then()
		.statusCode(org.springframework.http.HttpStatus.NOT_FOUND.value());
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
			.body(jsonCorrectChineseKitchen)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(org.springframework.http.HttpStatus.CREATED.value());
	}
	
	@Test
	public void mustHave2Kitchens_whenListKitchen() {
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(2));
	}

	
}
