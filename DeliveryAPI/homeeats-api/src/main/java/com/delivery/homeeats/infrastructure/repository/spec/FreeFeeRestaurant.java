package com.delivery.homeeats.infrastructure.repository.spec;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.delivery.homeeats.domain.model.Restaurant;


public class FreeFeeRestaurant implements Specification<Restaurant> {

	
	private static final long serialVersionUID = 1L;

	
	@Override
	public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		return criteriaBuilder.equal(root.get("deliveryFee"), BigDecimal.ZERO);
	}

}
