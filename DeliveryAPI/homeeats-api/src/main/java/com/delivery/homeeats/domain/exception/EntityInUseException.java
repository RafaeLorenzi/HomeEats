package com.delivery.homeeats.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EntityInUseException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public EntityInUseException(String message) {
		super(message);
	}

}
