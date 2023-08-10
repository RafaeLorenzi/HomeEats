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
	
	private static final String MSG_RESTAURANT_NOT_FOUND = "There is no registered restaurant with the ID %d.";

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRegistrationService kitchenRegistrationService;
	
	public Restaurant addRestaurant(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		
		Kitchen kitchen = kitchenRegistrationService.findOrFail(kitchenId);
		
//		Kitchen kitchen = kitchenRepository.findById(kitchenId)
//				.orElseThrow(() -> new EntityNotExistException(
//						String.format("There is no registered kitchen with the ID %d." , kitchenId)));
		
		
		restaurant.setKitchen(kitchen);
		
		return restaurantRepository.save(restaurant); 
	}
	
	public Restaurant findOrFail(Long restaurantId) {
		return restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new EntityNotExistException(
						String.format(MSG_RESTAURANT_NOT_FOUND , restaurantId)));
	}

}
