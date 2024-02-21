package com.admindroid.spring.springboot.bookmyshow.boot.exception;

public class ScreenNotFound extends RuntimeException{
	String message;
	
	public String getMessage() {
		return message;
	}

	public ScreenNotFound(String message) {
		this.message = message;
	}
	
}
