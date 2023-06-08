package com.delivery.homeeats.domain.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.homeeats.domain.model.Municipalities;

@Repository
public interface MunicipalitiesRepository extends JpaRepository<Municipalities, Long> {
	

}
