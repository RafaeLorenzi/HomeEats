package com.delivery.homeeats.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.model.Restaurant;
import com.delivery.homeeats.domain.repository.KitchenRepository;
import com.delivery.homeeats.domain.repository.RestaurantRepository;

@Service
public class RestaurantResgistrationService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Restaurant addRestaurant(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenRepository.findById(kitchenId)
				.orElseThrow(() -> new EntityNotExistException(
						String.format("There is no registered kitchen with the ID %d." , kitchenId)));
		
		
		restaurant.setKitchen(kitchen);
		
		return restaurantRepository.save(restaurant); 
	}
	

}
