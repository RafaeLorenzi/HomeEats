package com.delivery.homeeats.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.delivery.homeeats.HomeeatsApiApplication;
import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.repository.KitchenRepository;

public class KitchenById {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(HomeeatsApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		KitchenRepository kitchenRepository  = applicationContext.getBean(KitchenRepository.class);
	
		Kitchen kitchen = kitchenRepository.findById(1L);
		
		System.out.println(kitchen.getName());
		
}
}
