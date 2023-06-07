package com.delivery.homeeats.domain.repository;

import java.util.List;

import com.delivery.homeeats.domain.model.Municipalities;

public interface MunicipalitiesRepository {
	
	List<Municipalities> list();
	Municipalities findById(Long id);
	Municipalities add(Municipalities municipalities);
	void remove(Municipalities municipalities);
	

}
