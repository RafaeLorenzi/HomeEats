package com.delivery.homeeats.infrastructure.repository;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.delivery.homeeats.domain.model.Restaurant;
import com.delivery.homeeats.domain.repository.RestaurantRepository;

@Component
public class RestaurantRepositoryImp implements RestaurantRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurant> list(){
		return manager.createQuery("from Restaurant", Restaurant.class)
				.getResultList();
	}
	
	@Override
	@Transactional
	public Restaurant add(Restaurant restaurant) {
		return manager.merge(restaurant);
		
	}
	@Override
	public Restaurant findById(Long id) {
		return manager.find(Restaurant.class, id);
	}
	@Override
	@Transactional
	public void remove(Restaurant restaurant) {
		restaurant = findById(restaurant.getId());
		manager.remove(restaurant);
		
	}

}
