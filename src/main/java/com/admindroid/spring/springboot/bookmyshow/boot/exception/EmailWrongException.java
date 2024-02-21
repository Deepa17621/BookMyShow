package com.admindroid.spring.springboot.bookmyshow.boot.exception;

public class EmailWrongException extends RuntimeException{
	String message;
	
	public String getMessage() {
		return message;
	}

	public EmailWrongException(String message) {
		this.message = message;
	}
	
}
