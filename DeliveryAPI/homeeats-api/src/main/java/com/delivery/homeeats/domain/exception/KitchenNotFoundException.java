package com.delivery.homeeats.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

 
public class KitchenNotFoundException extends EntityNotExistException {
	
	private static final long serialVersionUID = 1L;
	
	public KitchenNotFoundException(String message) {
		super(message);
	}

	public KitchenNotFoundException (Long kitchenId) {
		this(String.format("There is no registered kitchen with the ID %d.", kitchenId));
	}
}
