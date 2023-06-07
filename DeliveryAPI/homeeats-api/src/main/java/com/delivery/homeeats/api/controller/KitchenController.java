package com.delivery.homeeats.api.controller;

import java.net.http.HttpHeaders;
import java.util.List;

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

import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
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
		return kitchenRepository.list();
		
	}
	
	
	@GetMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> findById(@PathVariable Long kitchenId) {
		Kitchen kitchen = kitchenRepository.findById(kitchenId);
		
		if(kitchen != null) {
			return ResponseEntity.ok(kitchen);
		}
		
		return ResponseEntity.notFound().build();
		  	
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Kitchen addKitchen(@RequestBody Kitchen kitchen) {
		return kitchenRegistrationService.addKitchen(kitchen);
	}
	
	@PutMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> updateKitchen(@PathVariable Long kitchenId, 
			@RequestBody Kitchen kitchen){
		Kitchen actualKitchen = kitchenRepository.findById(kitchenId);
		
		if(actualKitchen != null) {
		BeanUtils.copyProperties(kitchen, actualKitchen, "id");
		
		actualKitchen = kitchenRegistrationService.addKitchen(actualKitchen);
		
		return ResponseEntity.ok(actualKitchen);
		}
		
		return ResponseEntity.notFound().build();
			
	}
	
	@DeleteMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> deleteKitchen(@PathVariable Long kitchenId){
		try {
				kitchenRegistrationService.remove(kitchenId);
				return ResponseEntity.noContent().build();		
	    	
		} catch (EntityNotExistException e) {
			return ResponseEntity.notFound().build();
								
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
	}
	
}

