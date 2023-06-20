package com.delivery.homeeats.domain.model.service.copy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.delivery.homeeats.domain.model.Kitchen;
import com.delivery.homeeats.domain.repository.KitchenRepository;

@Service
public class KitchenRegistrationService {
	
	@Autowired
	private KitchenRepository kitchenRepository;
	
	public Kitchen  addKitchen(Kitchen Kitchen) {
		return kitchenRepository.save(Kitchen);
		
	}
	
	public void remove(Long kitchenId) {
		try {
			kitchenRepository.deleteById(kitchenId);
		
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotExistException(
					String.format("There is no registered kitchen with the ID %d." , kitchenId));
			
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
					String.format("The kitchen with ID %d cannot be removed as it is in use.", kitchenId));
		}
	}

}
