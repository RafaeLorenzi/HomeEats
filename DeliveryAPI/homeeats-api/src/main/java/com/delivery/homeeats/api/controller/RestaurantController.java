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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.homeeats.domain.exception.BusinessException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.model.Restaurant;
import com.delivery.homeeats.domain.model.service.DistrictResgistrationService;
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
	public Restaurant findById(@PathVariable Long restaurantId){
		
		return restaurantResgistrationService.findOrFail(restaurantId);
	}
	

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
		try {
			return restaurantResgistrationService.addRestaurant(restaurant);
		} catch (EntityNotExistException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	

	
	
	@PutMapping("/{restaurantId}")
	public Restaurant updateRestaurant(@PathVariable Long restaurantId,
			@RequestBody Restaurant restaurant) {
		Restaurant actualRestaurant = restaurantResgistrationService.findOrFail(restaurantId);
		
		BeanUtils.copyProperties(restaurant, actualRestaurant,
				"id", "paymentMethod", "adress");
		
		try {
			return restaurantResgistrationService.addRestaurant(actualRestaurant);
		} catch (EntityNotExistException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	
	
	@PatchMapping("/{restaurantId}")
	public Restaurant parcialUpdate(@PathVariable Long restaurantId,
			@RequestBody Map<String, Object> fields){
		
		Restaurant actualRestaurant = restaurantResgistrationService.findOrFail(restaurantId);
		
		merge(fields, actualRestaurant);
		
		return updateRestaurant(restaurantId, actualRestaurant);
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
	

