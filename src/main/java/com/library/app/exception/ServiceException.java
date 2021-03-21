package com.library.app.exception;

public class ServiceException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4817673744291452045L;
	private final Error error;
	
	public ServiceException(Error error) {
		super();
		this.error = error;
	}
	
	public Error getError() {
		return this.error;
	}
	
}
