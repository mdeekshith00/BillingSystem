package com.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	public ResourceNotFound(String resourceName ,String fieldName , Object fieldValue ) {
		super(String.format("%s  not found with %s  : '%s' " ,resourceName ,  fieldValue,fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
	
		
	}

}
