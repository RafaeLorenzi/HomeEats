package com.delivery.homeeats.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.homeeats.domain.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	
	List<Restaurant> findByDeliveryFeeBetween (BigDecimal InicialFee, BigDecimal finalFee);
	
	List<Restaurant>  findByNameContainingAndKitchenId (String name, Long kitchenId);
	
	


}
