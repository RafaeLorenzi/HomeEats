package com.delivery.homeeats.domain.repository;

import java.util.List;



import com.delivery.homeeats.domain.model.Restaurant;

public interface RestaurantRepository {
	
	List<Restaurant> list();
	Restaurant findById(Long id);
	Restaurant add(Restaurant restaurant);
	void remove(Restaurant restaurant);
	


}
