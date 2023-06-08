package com.delivery.homeeats.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.model.District;
import com.delivery.homeeats.domain.model.service.DistrictResgistrationService;
import com.delivery.homeeats.domain.repository.DistrictRepository;

@RestController
@RequestMapping("/districts")
public class DistrictController {
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@Autowired
	private DistrictResgistrationService districtResgistrationService;
	
	@GetMapping
	public List<District> districtsList(){
		return districtRepository.findAll();
	}

	@GetMapping("/{districtId}")
	public ResponseEntity<District> findById(@PathVariable Long districtId){
		Optional<District> district = districtRepository.findById(districtId);
		
		if(district.isPresent()) {
			return ResponseEntity.ok(district.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public District addDistrict(@RequestBody District district) {
		return districtResgistrationService.addDistrict(district);
	}
	
	@PutMapping("/{districtId}")
	public ResponseEntity<District> updateDistric(@PathVariable Long districtId,
			@RequestBody District district){
		Optional<District> actualDistrict = districtRepository.findById(districtId);
		
		if(actualDistrict.isPresent()) {
			BeanUtils.copyProperties(district, actualDistrict, "id");
			
			 District districtSaved = districtResgistrationService.addDistrict(actualDistrict.get());
			
			return ResponseEntity.ok(districtSaved);
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{districtId}")
	public ResponseEntity<District> deleteDistrict(@PathVariable Long districtId){
		try {
			districtResgistrationService.remove(districtId);
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotExistException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
