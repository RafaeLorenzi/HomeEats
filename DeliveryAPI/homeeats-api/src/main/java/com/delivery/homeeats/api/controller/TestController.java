package com.delivery.homeeats.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.model.Restaurant;
import com.delivery.homeeats.domain.repository.KitchenRepository;
import com.delivery.homeeats.domain.repository.RestaurantRepository;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@GetMapping("/kitchens/by-name")
	public List<Kitchen> kitchenByName(@RequestParam("name") String name){
		return kitchenRepository.findByNameContaining(name);
	}
	@GetMapping("/restaurants/by-delivery-fee")
	public List<Restaurant> findByDeliveryFeeBetween (BigDecimal InicialFee, BigDecimal finalFee){
		return restaurantRepository.findByDeliveryFeeBetween(InicialFee, finalFee);	
	}
	@GetMapping("/restaurants/by-kitchen")
	public List<Restaurant> findByKitchen (String name, Long kitchenId){
		return restaurantRepository.findByNameContainingAndKitchenId ( name,  kitchenId);	
	}


}
