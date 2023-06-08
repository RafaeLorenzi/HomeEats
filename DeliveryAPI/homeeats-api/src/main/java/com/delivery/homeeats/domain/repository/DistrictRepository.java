package com.delivery.homeeats.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.homeeats.domain.model.District;

@Repository
public interface DistrictRepository  extends JpaRepository<District, Long>{
	
	

}
