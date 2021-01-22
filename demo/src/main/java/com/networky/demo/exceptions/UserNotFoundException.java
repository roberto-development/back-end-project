package com.networky.demo.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException(String message, Throwable cause) {
		super(message);
	}
	
	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

}
