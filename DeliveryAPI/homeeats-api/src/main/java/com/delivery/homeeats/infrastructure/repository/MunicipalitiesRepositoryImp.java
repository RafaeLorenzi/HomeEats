package com.delivery.homeeats.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.delivery.homeeats.domain.model.Municipalities;
import com.delivery.homeeats.domain.repository.MunicipalitiesRepository;

@Component
public class MunicipalitiesRepositoryImp implements MunicipalitiesRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Municipalities> list(){
		return manager.createQuery("from municipalities", Municipalities.class)
				.getResultList();
	}
	@Override
	@Transactional
	public Municipalities add(Municipalities municipalities) {
		return manager.merge(municipalities);
		
	}
	@Override
	public Municipalities findById(Long id) {
		return manager.find(Municipalities.class, id);
	}
	@Override
	@Transactional
	public void remove(Municipalities municipalities) {
		municipalities = findById(municipalities.getId());
		manager.remove(municipalities);
		
	}

}
