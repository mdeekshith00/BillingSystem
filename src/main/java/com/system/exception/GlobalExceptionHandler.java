package com.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleProductsNotFoundException(ErrorResponse exception) {
		ErrorResponse  resourceNotFound = new ErrorResponse("Products", "pId", exception.getMessage());
		return new ResponseEntity<>(resourceNotFound , HttpStatus.OK);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(ProductNotFoundException productex) {
		ErrorResponse  resourceNotFound = new ErrorResponse("BillingSystem", "uknown", productex.getMessage());
		return new ResponseEntity<>(resourceNotFound , HttpStatus.OK);
		
	}
	
	

}


