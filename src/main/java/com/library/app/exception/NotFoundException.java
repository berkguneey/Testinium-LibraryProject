package com.library.app.exception;

public class NotFoundException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8621423897627805477L;

	public NotFoundException(Error error) {
		super(error);
	}

}
