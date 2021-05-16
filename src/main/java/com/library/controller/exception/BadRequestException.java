package com.library.controller.exception;

public class BadRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -834998148767110580L;
	
	public BadRequestException(String errorMessage) {
		super(errorMessage);
	}

}
