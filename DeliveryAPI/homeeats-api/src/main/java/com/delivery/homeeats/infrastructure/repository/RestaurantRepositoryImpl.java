package com.delivery.homeeats.infrastructure.repository;

import static com.delivery.homeeats.infrastructure.repository.spec.RestaurantSpecs.withFreeFee;

import static com.delivery.homeeats.infrastructure.repository.spec.RestaurantSpecs.withSimilarName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.delivery.homeeats.domain.model.Restaurant;
import com.delivery.homeeats.domain.repository.RestaurantRepository;
import com.delivery.homeeats.domain.repository.RestaurantRepositoryQueries;





@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired @Lazy
	private RestaurantRepository restaurantRepository;
	
	@Override
	public List<Restaurant> find(String name, BigDecimal inicialFee, BigDecimal finalFee) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
		Root<Restaurant> root = criteria.from(Restaurant.class);
		
		var predicates = new ArrayList<Predicate>();
		
		
		if (StringUtils.hasText(name)) {
			predicates.add(builder.like(root.get("name"), "%" + name + "%"));
		}
		
		if (inicialFee != null) {
			predicates.add(builder
				.greaterThanOrEqualTo(root.get("deliveryFee"), inicialFee));
		}
		
		if (finalFee != null) {
			predicates.add(builder
				.lessThanOrEqualTo(root.get("deliveryFee"), finalFee));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Restaurant> query = manager.createQuery(criteria);
		return query.getResultList();
					
	}

	@Override
	public List<Restaurant> findFreeFee(String name) {
		
		return restaurantRepository.findAll(withFreeFee()
				.and(withSimilarName(name)));
	}				

}
