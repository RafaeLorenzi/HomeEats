package com.delivery.homeeats.api.controller;

import java.util.List;

import java.util.Optional;

import javax.validation.Valid;

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

import com.delivery.homeeats.domain.exception.BusinessException;
import com.delivery.homeeats.domain.exception.DistrictNotFoundException;
import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.model.District;
import com.delivery.homeeats.domain.service.DistrictResgistrationService;
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
	public District findById(@PathVariable Long districtId){
	
		return districtResgistrationService.findOrFail(districtId);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public District addDistrict(@RequestBody @Valid District district) {
		try {
			return districtResgistrationService.addDistrict(district);
		} catch ( DistrictNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@PutMapping("/{districtId}")
	public District updateDistric(@PathVariable Long districtId,
			@RequestBody District district){
		District actualDistrict = districtResgistrationService.findOrFail(districtId);
		
		BeanUtils.copyProperties(district, actualDistrict, "id");
			
		try {
			return districtResgistrationService.addDistrict(actualDistrict);
		} catch ( DistrictNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	
	@DeleteMapping("/{districtId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDistrict(@PathVariable Long districtId){
		 districtResgistrationService.remove(districtId);
	}

}
