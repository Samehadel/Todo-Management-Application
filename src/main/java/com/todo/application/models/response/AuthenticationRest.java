package com.todo.application.models.response;

public class AuthenticationRest {

	private String message;

	
	public AuthenticationRest(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
