package com.delivery.homeeats.api.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.delivery.homeeats.domain.exception.BusinessException;
import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(EntityNotExistException.class)
	public ResponseEntity<?> handlerBusinessException(
			EntityNotExistException e) {
		
		Problem problem = Problem.builder()
				.dateTime(LocalDateTime.now())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(problem);
		
	}
	
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handlerBusinessException(
			BusinessException e) {
		
		Problem problem = Problem.builder()
				.dateTime(LocalDateTime.now())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(problem);
		
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> handlerHttpMediaTypeNotSupportedException(){
		
		Problem problem = Problem.builder()
				.dateTime(LocalDateTime.now())
				.message("The Media type is not accepted").build();
		
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(problem);
	}
	
	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<?> handlerEntityInUseException(EntityInUseException e){
		
		Problem problem = Problem.builder()
				.dateTime(LocalDateTime.now())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(problem);
	}

}
