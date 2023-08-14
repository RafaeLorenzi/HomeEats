package com.delivery.homeeats.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

 @ResponseStatus(value = HttpStatus.BAD_REQUEST) // , reason = "Entity not found")
public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
