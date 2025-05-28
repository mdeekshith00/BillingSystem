package com.system.exception;

public class ProductNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -862453830585241279L;

	public ProductNotFoundException(String message) {
		super(message);
	}

}
