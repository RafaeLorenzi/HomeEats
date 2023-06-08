package com.delivery.homeeats.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.Mergeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.model.Municipalities;
import com.delivery.homeeats.domain.model.service.MunicipalitiesRegistrationService;
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
	public ResponseEntity<Municipalities> findById(@PathVariable Long municipalitiesId){
		Optional<Municipalities> municipalities = municipalitiesRepository.findById(municipalitiesId);
		
		if(municipalities.isPresent()) {
			return ResponseEntity.ok(municipalities.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> addMunicipalitie(@RequestBody Municipalities municipalities){
		try {
			municipalities = municipalitiesRegistrationService.addMunicipalities(municipalities);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(municipalities);
		} catch (EntityNotExistException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{municipalitiesId}")
	public ResponseEntity<?> updateMunicipalitie(@PathVariable Long municipalitiesId,
			@RequestBody Municipalities municipalities){
			
		try {
			Optional<Municipalities> actualMunicipalities = municipalitiesRepository.findById(municipalitiesId);
			
			if(actualMunicipalities.isPresent()) {
				BeanUtils.copyProperties(municipalities, actualMunicipalities, "id");
				
				Municipalities municipalitiesSaved = municipalitiesRegistrationService.addMunicipalities(actualMunicipalities.get());
				return ResponseEntity.ok(municipalitiesSaved);
			}
			
			return ResponseEntity.notFound().build();
			
		} catch (EntityNotExistException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{municipalitiesId}")
	public ResponseEntity<Municipalities> deleteMunicipalitie(@PathVariable Long municipalitiesId){
		try {
			
			municipalitiesRegistrationService.deleteMunicipalitie(municipalitiesId);
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotExistException e) {
			return ResponseEntity.notFound().build();
			
		}catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	

	
	

}
