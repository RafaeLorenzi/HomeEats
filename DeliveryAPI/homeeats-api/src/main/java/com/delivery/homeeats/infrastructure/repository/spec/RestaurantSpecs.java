package com.delivery.homeeats.infrastructure.repository.spec;

import java.math.BigDecimal;


import org.springframework.data.jpa.domain.Specification;

import com.delivery.homeeats.domain.model.Restaurant;

public class RestaurantSpecs {
	
	public static Specification<Restaurant> withFreeFee(){
		return (root, query, builder) -> 
			builder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurant> withSimilarName(String name){
		return (root, query, builder) -> 
			builder.like(root.get("name"), "%" + name + "%");
	}

}
