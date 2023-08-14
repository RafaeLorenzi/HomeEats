package com.delivery.homeeats.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

 // @ResponseStatus(value = HttpStatus.NOT_FOUND) 
public class MunicipalitiesNotFoundException extends EntityNotExistException {
	
	private static final long serialVersionUID = 1L;
	
	public MunicipalitiesNotFoundException(String message) {
		super(message);
	}

	public MunicipalitiesNotFoundException (Long municipalitiesId) {
		this(String.format("There is no registered municipalitie with the ID %d.", municipalitiesId));
	}
}
