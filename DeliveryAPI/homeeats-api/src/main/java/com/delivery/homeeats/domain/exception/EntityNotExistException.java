package com.delivery.homeeats.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;


public abstract class EntityNotExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public EntityNotExistException(String message) {
		super(message);
	}

}
