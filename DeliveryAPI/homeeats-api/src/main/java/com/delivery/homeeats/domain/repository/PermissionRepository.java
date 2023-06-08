package com.delivery.homeeats.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.homeeats.domain.model.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
