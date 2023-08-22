package com.delivery.homeeats.api.exceptionHandler;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.delivery.homeeats.domain.exception.BusinessException;
import com.delivery.homeeats.domain.exception.EntityInUseException;
import com.delivery.homeeats.domain.exception.EntityNotExistException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	private static final String MSG_GENERIC_ERROR_FINAL_USER = 
			"An unexpected internal error occurred in the system." +
					"Please try again, and if the issue persists, contact us.";

	@Autowired
	private MessageSource messageSource;
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.INVALID_DATA;
		String detail = String.format(
				"One or more fields are invalid. Please fill in the correct information and try again.");
		
		BindingResult bindingResult = ex.getBindingResult();
		
	    List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
	    		.map(fieldError -> {
	    			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	    			
	    			return Problem.Field.builder()
	    				.name(fieldError.getField())
	    				.userMessage(message)
	    				.build();
	    		})
	    		.collect(Collectors.toList());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
			.userMessage(detail)
			.fields(problemFields)
		    .build();		
				
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.SYSTEM_ERROR;
		String detail = String.format(MSG_GENERIC_ERROR_FINAL_USER);
		
		
        ex.printStackTrace();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
		String detail = "The request body is invalid, please check for syntax errors.";
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_GENERIC_ERROR_FINAL_USER)
				.build();
		
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	
	private ResponseEntity<Object> handlePropertyBindingException(PropertyBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		// I created the joinPath method to reuse it in all the methods that need it
				// Concatenate the property names (separated by ".")
		
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
		String detail = String.format("The property '%s' does not exist" + 
				" Please correct or remove this property and try again.", path);	
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_GENERIC_ERROR_FINAL_USER)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatch(
					(MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
	
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
			MethodArgumentTypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.INVALID_PARAMETER;
		
		String detail = String.format( "The URL parameter '%s' received the value '%s'" +
		"which is of an invalid type. Please correct it and provide a value compatible with the type %s.",
		ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName() );
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_GENERIC_ERROR_FINAL_USER)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
				
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, 
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
		String detail = String.format("The resource %s you tried to access does not exist." 
				, ex.getRequestURL());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_GENERIC_ERROR_FINAL_USER)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
				
	}
	
	
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.MESSAGE_NOT_READABLE;
		String detail = String.format("The property '%s' received the value '%s'" + 
		"which is of an invalid type. Please correct it and provide a value compatible with the type %s" ,
		path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_GENERIC_ERROR_FINAL_USER)
				.build();
		
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}



	@ExceptionHandler(EntityNotExistException.class)
	public ResponseEntity<?> handleEntityNotExistException(
			EntityNotExistException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_GENERIC_ERROR_FINAL_USER)
				.build();
		
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	
		
	}
	
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusinessException(
			BusinessException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.BUSINESS_ERROR;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_GENERIC_ERROR_FINAL_USER)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
		
	}
	

	
	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<?> handleEntityInUseException(
			EntityInUseException ex, WebRequest request ){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTITY_IN_USE;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	

	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 
		if (body == null) {
			body = Problem.builder()
					.title(status.getReasonPhrase())
					.status(status.value())
					.userMessage(MSG_GENERIC_ERROR_FINAL_USER)
					.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.title((String) body)
					.status(status.value())
					.userMessage(MSG_GENERIC_ERROR_FINAL_USER)
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder (HttpStatus status,
			ProblemType problemType, String  detail) {
		
		return Problem.builder()
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
	}
	
	private String joinPath(List<Reference> references) {
		return references.stream()
			.map(ref -> ref.getFieldName())
			.collect(Collectors.joining("."));
	}
	
	

}
