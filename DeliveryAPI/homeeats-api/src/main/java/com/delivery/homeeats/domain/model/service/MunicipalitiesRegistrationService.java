package com.delivery.homeeats.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.model.District;
import com.delivery.homeeats.domain.model.Municipalities;
import com.delivery.homeeats.domain.repository.DistrictRepository;
import com.delivery.homeeats.domain.repository.MunicipalitiesRepository;

@Service
public class MunicipalitiesRegistrationService {
	
	private static final String MSG_MUNICIPALITIE_IN_USE =
			"The municipalitie with ID %d cannot be removed as it is in use.";

	
	private static final String MSG_MUNICIPALITIE_NOT_FOUND = 
			"There is no registered municipalitie with the ID %d.";

	
	@Autowired
	private MunicipalitiesRepository municipalitiesRepository;
	
	@Autowired
	private DistrictResgistrationService districtResgistrationService;
	
	public Municipalities addMunicipalities(Municipalities municipalities) {
		Long districtId = municipalities.getDistrict().getId();
		
		District district = districtResgistrationService.findOrFail(districtId);
		
//		District district = districtRepository.findById(districtId)
//				.orElseThrow(() -> new EntityNotExistException( 
//						String.format("There is no registered district with the ID %d." , districtId)));
		
		
		municipalities.setDistrict(district);
		
		return municipalitiesRepository.save(municipalities);
	}
	
	public void deleteMunicipalitie(Long municipalitiesId) {
		try {
			municipalitiesRepository.deleteById(municipalitiesId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotExistException( 
					String.format(MSG_MUNICIPALITIE_NOT_FOUND , municipalitiesId));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_MUNICIPALITIE_IN_USE, municipalitiesId));
		}
	}
	
	public Municipalities findOrFail(Long municipalitiesId) {
		return municipalitiesRepository.findById(municipalitiesId)
				.orElseThrow(() -> new EntityNotExistException(
						String.format(MSG_MUNICIPALITIE_NOT_FOUND , municipalitiesId)));
	}

}
