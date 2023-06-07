package com.delivery.homeeats.domain.repository;

import java.util.List;

import com.delivery.homeeats.domain.model.Permission;

public interface PermissionRepository {
	
	List<Permission> list();
	Permission findById(Long Id);
	Permission add(Permission permission);
	void remove(Permission permission);
	

}
