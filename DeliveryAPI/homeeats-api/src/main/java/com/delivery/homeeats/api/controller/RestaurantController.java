package com.delivery.homeeats.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.homeeats.Groups;
import com.delivery.homeeats.domain.exception.BusinessException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.exception.RestaurantNotFoundException;
import com.delivery.homeeats.domain.model.Restaurant;
import com.delivery.homeeats.domain.model.service.DistrictResgistrationService;
import com.delivery.homeeats.domain.model.service.RestaurantResgistrationService;
import com.delivery.homeeats.domain.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
	public Restaurant addRestaurant(@RequestBody @Validated(Groups.RestaurantRegister.class) Restaurant restaurant) {
		try {
			return restaurantResgistrationService.addRestaurant(restaurant);
		} catch (RestaurantNotFoundException e) {
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
		} catch (RestaurantNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	
	
	@PatchMapping("/{restaurantId}")
	public Restaurant parcialUpdate(@PathVariable Long restaurantId,
			@RequestBody Map<String, Object> fields, HttpServletRequest request){
		
		Restaurant actualRestaurant = restaurantResgistrationService.findOrFail(restaurantId);
		
		merge(fields, actualRestaurant, request);
		
		return updateRestaurant(restaurantId, actualRestaurant);
	}
	
	
	private void merge(Map<String, Object> fieldsOrigin, Restaurant restaurant
			, HttpServletRequest request) {
	
		ServletServerHttpRequest  serverHttpRequest = new ServletServerHttpRequest(request);
			
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			Restaurant restaurantOrigin = objectMapper.convertValue(fieldsOrigin, Restaurant.class);
			
			fieldsOrigin.forEach((propertieName, propertieValue) -> { 
				Field field = ReflectionUtils.findField(Restaurant.class, propertieName);
				field.setAccessible(true);
				
				Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
				
				ReflectionUtils.setField(field, restaurant, newValue);		
			}); 
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}	
	}
}
	

