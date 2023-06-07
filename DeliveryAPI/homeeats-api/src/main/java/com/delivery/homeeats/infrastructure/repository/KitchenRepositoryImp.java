package com.delivery.homeeats.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.repository.KitchenRepository;

@Component
public class KitchenRepositoryImp implements KitchenRepository {
	
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Kitchen> list(){
		return manager.createQuery("from Kitchen", Kitchen.class)
				.getResultList();
	}
	@Override
	@Transactional
	public Kitchen add(Kitchen kitchen) {
		return manager.merge(kitchen);
		
	}
	@Override
	public Kitchen findById(Long id) {
		return manager.find(Kitchen.class, id);
	}
	@Override
	@Transactional
	public void remove(Long kitchenId) {
		Kitchen kitchen = findById(kitchenId);
		
		if(kitchen == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(kitchen);
		
	}
}
