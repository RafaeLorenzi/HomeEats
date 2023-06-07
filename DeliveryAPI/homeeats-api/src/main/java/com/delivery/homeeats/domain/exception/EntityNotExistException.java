package com.delivery.homeeats.domain.exception;

public class EntityNotExistException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public EntityNotExistException(String message) {
		super(message);
	}

}
