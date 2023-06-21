package com.delivery.homeeats.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
	
	@GetMapping("/kitchens/exists-by-name")
	public boolean existskitchenByName(String name) {
		return kitchenRepository.existsByName(name);
	}
	
	
	@GetMapping("/restaurants/by-delivery-fee")
	public List<Restaurant> findByDeliveryFeeBetween (BigDecimal InicialFee
			, BigDecimal finalFee){
		
		return restaurantRepository.findByDeliveryFeeBetween(InicialFee, finalFee);	
	}
	
	@GetMapping("/restaurants/by-kitchen")
	public List<Restaurant> findByKitchen (String name
			, Long kitchenId){
		
		return restaurantRepository.consultRestaurantsByName ( name,  kitchenId);	
	}
	
	
	@GetMapping("/restaurants/first-by-name")
	public 	Optional<Restaurant> firstRestaurantByName(String name){
		return restaurantRepository.findFirstRestaurantByNameContaining(name);
	}
	 
	@GetMapping("/restaurants/top2-by-name")
	public List<Restaurant> top2RestaurantsByName(String name){
		return restaurantRepository.findTop2ByNameContaining(name);	
	}
	
	@GetMapping("/restaurants/count-by-kitchen")
	public int countRestaurantsBykitchenId(Long kitchenId) {
		return restaurantRepository.countBykitchenId(kitchenId);
	}
	
	@GetMapping("/restaurants/by-name-and-fee")
	public List<Restaurant> restaurantByNameFee(String name, BigDecimal inicialFee
			, BigDecimal finalFee){
		
		return restaurantRepository.find(name, inicialFee, finalFee);
	}
	
	@GetMapping("/restaurant/with-free-fee")
	public List<Restaurant> freeFeeRestaurants(String name){
	
		
		return restaurantRepository.findFreeFee(name);
			
	}
	
	@GetMapping("/restaurant/first")
	public Optional<Restaurant> firstRestaurant(){
		return restaurantRepository.findFirst();
	}
}
