package com.delivery.homeeats.api.controller;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.homeeats.api.exceptionHandler.Problem;
import com.delivery.homeeats.domain.exception.BusinessException;
import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.exception.MunicipalitiesNotFoundException;
import com.delivery.homeeats.domain.model.Municipalities;
import com.delivery.homeeats.domain.service.MunicipalitiesRegistrationService;
import com.delivery.homeeats.domain.repository.MunicipalitiesRepository;

@RestController
@RequestMapping(value = "/municipalities")
public class MunicipalitiesController {
	
	@Autowired
	private MunicipalitiesRepository municipalitiesRepository;
	
	@Autowired
	private MunicipalitiesRegistrationService municipalitiesRegistrationService;
	
	@GetMapping
	public List<Municipalities> municipalitiesList(){
		return municipalitiesRepository.findAll();
	}
	
	@GetMapping("/{municipalitiesId}")
	public Municipalities findById(@PathVariable Long municipalitiesId){
		return municipalitiesRegistrationService.findOrFail(municipalitiesId);
	}
	

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Municipalities addMunicipalities(@RequestBody @Valid Municipalities municipalities) {
		try {
			return municipalitiesRegistrationService.addMunicipalities(municipalities);
		} catch (MunicipalitiesNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
			
		}
	}
	
	
	@PutMapping("/{municipalitiesId}")
	public Municipalities updateMunicipalitie(@PathVariable Long municipalitiesId,
			@RequestBody Municipalities municipalities){
		
		Municipalities actualMunicipalities = municipalitiesRegistrationService.findOrFail(municipalitiesId);
		
		BeanUtils.copyProperties(municipalities, actualMunicipalities, "id");
		
		try {
			return municipalitiesRegistrationService.addMunicipalities(actualMunicipalities);
		} catch (MunicipalitiesNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
			
		}
	
		
	}
	
	@DeleteMapping("/{municipalitiesId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMunicipalitie(@PathVariable Long municipalitiesId){
		 municipalitiesRegistrationService.deleteMunicipalitie(municipalitiesId);
	}
	
	
	
	

}
