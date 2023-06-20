package com.delivery.homeeats.infrastructure.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.delivery.homeeats.domain.model.Restaurant;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class RestaurantByName implements Specification<Restaurant>{

	
	private static final long serialVersionUID = 1L;
	
	private String name;

	@Override
	public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		
		return criteriaBuilder.like(root.get("name"), "%" +  name  + "%");
	}

}
