package com.delivery.homeeats.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

 // @ResponseStatus(value = HttpStatus.NOT_FOUND) 
public class DistrictNotFoundException extends EntityNotExistException {
	
	private static final long serialVersionUID = 1L;
	
	public DistrictNotFoundException(String message) {
		super(message);
	}

	public DistrictNotFoundException (Long districtId) {
		this(String.format("There is no registered district with the ID %d.", districtId));
	}
}
