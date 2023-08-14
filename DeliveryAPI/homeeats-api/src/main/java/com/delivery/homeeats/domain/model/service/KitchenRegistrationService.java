package com.delivery.homeeats.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.exception.KitchenNotFoundException;
import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.repository.KitchenRepository;

@Service
public class KitchenRegistrationService {
	
	private static final String MSG_KITCHEN_IN_USE 
		= "The kitchen with ID %d cannot be removed as it is in use.";

	private static final String MSG_KITCHEN_NOT_FOUND
		= "There is no registered kitchen with the ID %d.";
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Kitchen  addKitchen(Kitchen Kitchen) {
		return kitchenRepository.save(Kitchen);
		
	}
	
	public void remove(Long kitchenId) {
		try {
			kitchenRepository.deleteById(kitchenId);
		
		} catch (EmptyResultDataAccessException e) {
			throw new KitchenNotFoundException(kitchenId);
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format(MSG_KITCHEN_IN_USE, kitchenId));
		}
	}
	
	public Kitchen findOrFail( Long kitchenId) {
		return kitchenRepository.findById(kitchenId)
				.orElseThrow(() -> new KitchenNotFoundException(kitchenId));
	}

}
