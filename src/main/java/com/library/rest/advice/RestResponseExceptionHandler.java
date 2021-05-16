package com.library.rest.advice;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.library.controller.exception.BadRequestException;

@RestControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	public RestResponseExceptionHandler() {
		super();
	}
	
	@ExceptionHandler({ConstraintViolationException.class})
	protected ResponseEntity<Object> handleValidationException(ConstraintViolationException exception, WebRequest request){
		Map<String, String> errorMessage = new HashMap<>();
		exception.getConstraintViolations().forEach(e->{
			errorMessage.put(e.getPropertyPath().toString(), e.getMessage());
		});
		return handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({BadRequestException.class})
	protected ResponseEntity<Object> handleBadRequest(BadRequestException exception, WebRequest request){
		Map<String, String> errorMessage = new HashMap<>();
		errorMessage.put("Message", exception.getMessage());
		return handleExceptionInternal(exception, errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
