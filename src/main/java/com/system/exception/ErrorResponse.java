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
	


}
