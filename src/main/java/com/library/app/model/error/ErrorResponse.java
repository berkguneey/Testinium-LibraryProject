package com.library.app.model.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

	private Boolean success;
	private String message;

	public ErrorResponse(Boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
}
