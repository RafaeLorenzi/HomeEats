package com.delivery.homeeats.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.delivery.homeeats.domain.model.Restaurant;

public interface RestaurantRepositoryQueries {
	
	List<Restaurant> find(String name, BigDecimal inicialFee, BigDecimal finalFee);
	
	List<Restaurant> findFreeFee(String name);

}
