package com.delivery.homeeats.api.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.homeeats.domain.model.Restaurant;
import com.delivery.homeeats.domain.repository.RestaurantRepository;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@GetMapping
	public List<Restaurant> restaurantList(){
		return restaurantRepository.list();
	}
	
	@GetMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> findById(@PathVariable Long restaurantId) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId);
		
		if(restaurant != null) {
			return ResponseEntity.ok(restaurant);
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
