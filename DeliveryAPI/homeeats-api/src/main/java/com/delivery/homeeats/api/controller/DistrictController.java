package com.delivery.homeeats.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.homeeats.domain.model.District;
import com.delivery.homeeats.domain.repository.DistrictRepository;

@RestController
@RequestMapping("/districts")
public class DistrictController {
	
	@Autowired
	private DistrictRepository districtRepository;
	
	@GetMapping
	public List<District> districtsList(){
		return districtRepository.list();
		
	}

}
