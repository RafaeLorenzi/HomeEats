package com.delivery.homeeats.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.homeeats.domain.exception.DistrictNotFoundException;
import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.model.District;
import com.delivery.homeeats.domain.repository.DistrictRepository;

@Service
public class DistrictResgistrationService {
	
	private static final String MSG_DISTRICT_IN_USE = 
			"The district with ID %d cannot be removed as it is in use.";
	
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Transactional
	public District addDistrict(District district) {
		return districtRepository.save(district);
	}
	
	@Transactional
	public void remove(Long districtId) {
		try {
			districtRepository.deleteById(districtId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new DistrictNotFoundException(districtId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_DISTRICT_IN_USE, districtId));
		}
	}
	
	public District findOrFail(Long districtId) {
		return districtRepository.findById(districtId)
				.orElseThrow(() -> new DistrictNotFoundException(districtId));
	}
	

}
