package com.delivery.homeeats.domain.repository;

import java.util.List;

import com.delivery.homeeats.domain.model.Kitchen;

public interface KitchenRepository {
	
	List<Kitchen> list();
	Kitchen findById(Long id);
	Kitchen add(Kitchen kitchen);
	void remove(Long kitchenId);
	

}
