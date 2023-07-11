package com.delivery.homeeats.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.delivery.homeeats.domain.model.Restaurant;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries, 
JpaSpecificationExecutor<Restaurant>{
	


}
