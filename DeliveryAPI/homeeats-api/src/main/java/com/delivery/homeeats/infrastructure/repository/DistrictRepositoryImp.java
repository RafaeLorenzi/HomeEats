package com.delivery.homeeats.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.homeeats.domain.model.District;

import com.delivery.homeeats.domain.repository.DistrictRepository;

@Component
public class DistrictRepositoryImp implements DistrictRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<District> list(){
		return manager.createQuery("from District", District.class)
				.getResultList();
	}
	@Override
	@Transactional
	public District add(District district) {
		return manager.merge(district);
		
	}
	@Override
	public District findById(Long id) {
		return manager.find(District.class, id);
	}
	@Override
	@Transactional
	public void remove(District district) {
		district = findById(district.getId());
		manager.remove(district);
		
	}

}
