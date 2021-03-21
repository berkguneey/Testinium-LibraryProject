package com.library.app.exception;

public class DataConflictException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1773042283821759663L;

	public DataConflictException(Error error) {
		super(error);
	}

}
