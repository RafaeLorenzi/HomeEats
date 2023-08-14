package com.delivery.homeeats.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

 // @ResponseStatus(value = HttpStatus.NOT_FOUND) 
public class RestaurantNotFoundException extends EntityNotExistException {
	
	private static final long serialVersionUID = 1L;
	
	public RestaurantNotFoundException(String message) {
		super(message);
	}

	public RestaurantNotFoundException (Long restaurantId) {
		this(String.format("There is no registered restaurant with the ID %d.", restaurantId));
	}
}
