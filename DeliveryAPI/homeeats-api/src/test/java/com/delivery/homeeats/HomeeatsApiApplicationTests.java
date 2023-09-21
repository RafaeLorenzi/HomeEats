package com.delivery.homeeats;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.sql.init.dependency.AbstractBeansOfTypeDatabaseInitializerDetector;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.model.service.KitchenRegistrationService;

@RunWith(SpringRunner.class)
@SpringBootTest
class RegisterKitchenIntegrationTests {
	
	@Autowired
	private KitchenRegistrationService kitchenRegistrationService;
	
	@Test
	public void testKitchenRegisterSuccess () {
		//Scenario
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName("chinese");
		
		//Action
		newKitchen = kitchenRegistrationService.addKitchen(newKitchen);
		
		//validation
		assertThat(newKitchen).isNotNull();
		assertThat(newKitchen.getId()).isNotNull();
		
	}
	
	@org.junit.Test ( expected = ConstraintViolationException.class)
	public void mostFailRegisterKitchen_withoutName() {
		
		//Scenario
		Kitchen newKitchen = new Kitchen();
		newKitchen.setName(null);
		
		//Action
		newKitchen = kitchenRegistrationService.addKitchen(newKitchen);
		

		
		
	}

	
}
