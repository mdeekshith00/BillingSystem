package com.system.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class ErrorResponse {

	private String resourceName;
	private String fieldName;
	private String message;
	
//	public ResourceNotFound(String resourceName ,String fieldName , Object fieldValue ) {
//		super(String.format("%s  not found with %s : '%s'" , resourceName,fieldName , fieldValue));
//		this.resourceName=resourceName;
//		this.fieldName=fieldName;
//	
//		
//	}

}
