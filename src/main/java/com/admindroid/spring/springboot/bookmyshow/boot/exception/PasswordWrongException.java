package com.admindroid.spring.springboot.bookmyshow.boot.exception;


public class PasswordWrongException extends RuntimeException{
	String message;
	
	public String getMessage() {
		return message;
	}

	public PasswordWrongException(String message) {
		this.message = message;
	}
	
}
