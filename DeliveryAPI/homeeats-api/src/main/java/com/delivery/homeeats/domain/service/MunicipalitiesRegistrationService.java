package com.delivery.homeeats.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.exception.MunicipalitiesNotFoundException;
import com.delivery.homeeats.domain.model.District;
import com.delivery.homeeats.domain.model.Municipalities;
import com.delivery.homeeats.domain.repository.DistrictRepository;
import com.delivery.homeeats.domain.repository.MunicipalitiesRepository;

@Service
public class MunicipalitiesRegistrationService {
	
	private static final String MSG_MUNICIPALITIE_IN_USE =
			"The municipalitie with ID %d cannot be removed as it is in use.";



	
	@Autowired
	private MunicipalitiesRepository municipalitiesRepository;
	
	@Autowired
	private DistrictResgistrationService districtResgistrationService;
	
	@Transactional
	public Municipalities addMunicipalities(Municipalities municipalities) {
		Long districtId = municipalities.getDistrict().getId();
		
		District district = districtResgistrationService.findOrFail(districtId);
		
//		District district = districtRepository.findById(districtId)
//				.orElseThrow(() -> new EntityNotExistException( 
//						String.format("There is no registered district with the ID %d." , districtId)));
		
		
		municipalities.setDistrict(district);
		
		return municipalitiesRepository.save(municipalities);
	}
	
	@Transactional
	public void deleteMunicipalitie(Long municipalitiesId) {
		try {
			municipalitiesRepository.deleteById(municipalitiesId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new MunicipalitiesNotFoundException( municipalitiesId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_MUNICIPALITIE_IN_USE, municipalitiesId));
		}
	}
	
	public Municipalities findOrFail(Long municipalitiesId) {
		return municipalitiesRepository.findById(municipalitiesId)
				.orElseThrow(() -> new MunicipalitiesNotFoundException( municipalitiesId));
	}

}
