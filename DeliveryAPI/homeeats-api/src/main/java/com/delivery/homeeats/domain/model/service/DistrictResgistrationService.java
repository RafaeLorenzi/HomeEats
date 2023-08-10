package com.delivery.homeeats.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.model.District;
import com.delivery.homeeats.domain.repository.DistrictRepository;

@Service
public class DistrictResgistrationService {
	
	private static final String MSG_DISTRICT_IN_USE = 
			"The district with ID %d cannot be removed as it is in use.";
	
	
	private static final String MSG_DISTRICT_NOT_FOUND = 
			"There is no registered district with the ID %d.";
	
	@Autowired
	private DistrictRepository districtRepository;
	
	public District addDistrict(District district) {
		return districtRepository.save(district);
	}
	
	public void remove(Long districtId) {
		try {
			districtRepository.deleteById(districtId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotExistException(
					String.format(MSG_DISTRICT_NOT_FOUND , districtId));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_DISTRICT_IN_USE, districtId));
		}
	}
	
	public District findOrFail(Long districtId) {
		return districtRepository.findById(districtId)
				.orElseThrow(() -> new EntityNotExistException(
						String.format(MSG_DISTRICT_NOT_FOUND)));
	}
	

}
