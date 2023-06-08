package com.delivery.homeeats.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.repository.KitchenRepository;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
//	@GetMapping("/kitchens/by-name")
//	public List<Kitchen> kitchenByName(@RequestParam("name") String name){
//		return kitchenRepository.listByName(name);
//	}

}
