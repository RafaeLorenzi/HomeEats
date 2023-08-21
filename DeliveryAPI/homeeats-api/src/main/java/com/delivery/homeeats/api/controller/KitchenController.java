package com.delivery.homeeats.api.controller;

import java.net.http.HttpHeaders;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.server.ResponseStatusException;

import com.delivery.homeeats.domain.exception.BusinessException;
import com.delivery.homeeats.domain.exception.DistrictNotFoundException;
import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.exception.KitchenNotFoundException;
import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.model.service.KitchenRegistrationService;
import com.delivery.homeeats.domain.repository.KitchenRepository;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	@Autowired
	private KitchenRegistrationService kitchenRegistrationService;
	
	@GetMapping
	public List<Kitchen> kitchenList(){
		return kitchenRepository.findAll();
		
	}
	
	
	@GetMapping("/{kitchenId}")
	public Kitchen findById(@PathVariable Long kitchenId) {
		return kitchenRegistrationService.findOrFail(kitchenId);
		
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen addKitchen(@RequestBody @Valid Kitchen kitchen) {
		
		try {
			return kitchenRegistrationService.addKitchen(kitchen);
		} catch (KitchenNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	
	@PutMapping("/{kitchenId}")
	public Kitchen updateKitchen(@PathVariable Long kitchenId, 
			@RequestBody @Valid Kitchen kitchen){
		Kitchen actualKitchen = kitchenRegistrationService.findOrFail(kitchenId);
		
		BeanUtils.copyProperties(kitchen, actualKitchen, "id");
		
		try {
			return kitchenRegistrationService.addKitchen(actualKitchen);
		} catch (KitchenNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
		
		

	@DeleteMapping("/{kitchenId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteKitchen(@PathVariable Long kitchenId){
	
				kitchenRegistrationService.remove(kitchenId);	
	
	}
	
}

