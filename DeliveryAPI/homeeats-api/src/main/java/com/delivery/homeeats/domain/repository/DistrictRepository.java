package com.delivery.homeeats.domain.repository;

import java.util.List;

import com.delivery.homeeats.domain.model.District;

public interface DistrictRepository {
	
	List<District> list();
	District findById(Long id);
	District add(District district);
	void remove(District district);

}
