package com.delivery.homeeats.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.model.Restaurant;
import com.delivery.homeeats.domain.model.service.RestaurantResgistrationService;
import com.delivery.homeeats.domain.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {
	
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantResgistrationService restaurantResgistrationService;
	
	@GetMapping
	public List<Restaurant> restaurantList(){
		return restaurantRepository.findAll();
	}
	
	@GetMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> findById(@PathVariable Long restaurantId) {
		Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
		
		if(restaurant.isPresent()) {
			return ResponseEntity.ok(restaurant.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant) {
		try {
		restaurant = restaurantResgistrationService.addRestaurant(restaurant);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(restaurant);
		} catch (EntityNotExistException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{restaurantId}")
	public ResponseEntity<?> updateRestaurant(@PathVariable Long restaurantId,
			@RequestBody Restaurant restaurant){
		
			try {
				Optional<Restaurant> actualRestaurant = restaurantRepository.findById(restaurantId);
				
				if(actualRestaurant.isPresent()) {
					BeanUtils.copyProperties(restaurant, actualRestaurant.get(), "id", "paymentMethod");
				
				Restaurant	restaurantSaved = restaurantResgistrationService.addRestaurant(actualRestaurant.get());
					return ResponseEntity.ok(restaurantSaved);
				
		    	}
				
				return ResponseEntity.notFound().build();
				
				
			}catch (EntityNotExistException e) {
				return ResponseEntity.badRequest()
						.body(e.getMessage());
			}
		
	
	}
	
	@PatchMapping("/{restaurantId}")
	public ResponseEntity<?> parcialUpdate(@PathVariable Long restaurantId,
			@RequestBody Map<String, Object> fields){
		Optional<Restaurant> actualRestaurant = restaurantRepository.findById(restaurantId);
		
		if(actualRestaurant.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		merge(fields, actualRestaurant.get());
		
		return updateRestaurant(restaurantId, actualRestaurant.get());
	}
	
	
	private void merge(Map<String, Object> fieldsOrigin, Restaurant restaurant) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant restaurantOrigin = objectMapper.convertValue(fieldsOrigin, Restaurant.class);
		
		
		fieldsOrigin.forEach((propertieName, propertieValue) -> { 
			Field field = ReflectionUtils.findField(Restaurant.class, propertieName);
			field.setAccessible(true);
			
			Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
			
		//	System.out.println(propertieName + " = " + propertieValue + " = " + newValue);
			
			ReflectionUtils.setField(field, restaurant, newValue);
			
			
		});
	}
}
	

