package com.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<?> handleProductsNotFoundException(ProductNotFoundException exception) {
		ResourceNotFound  resourceNotFound = new ResourceNotFound("Products", "pId", "pId");
		return new ResponseEntity<>(resourceNotFound , HttpStatus.OK);
		
	}
	

}
