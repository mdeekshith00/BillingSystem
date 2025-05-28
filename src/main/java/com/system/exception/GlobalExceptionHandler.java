package com.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleProductsNotFoundException(ProductNotFoundException exception) {
		ErrorResponse  error = new ErrorResponse("Products", "pId", exception.getMessage());
		return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception ex) {
		ErrorResponse  error = new ErrorResponse("BillingSystem", "uknown", ex.getMessage());
		return new ResponseEntity<>(error , HttpStatus.NOT_FOUND);
		
	}
	
	

}


