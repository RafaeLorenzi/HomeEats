package com.delivery.homeeats.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import com.delivery.homeeats.domain.model.Permission;
import com.delivery.homeeats.domain.repository.PermissionRepository;

@Component
public class PermissionRepositoryImp implements PermissionRepository{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permission> list(){
		return manager.createQuery("from permission", Permission.class)
				.getResultList();
	}
	@Override
	@Transactional
	public Permission add(Permission permission) {
		return manager.merge(permission);
		
	}
	@Override
	public Permission findById(Long id) {
		return manager.find(Permission.class, id);
	}
	@Override
	@Transactional
	public void remove(Permission permission) {
		permission = findById(permission.getId());
		manager.remove(permission);
		
	}

}
