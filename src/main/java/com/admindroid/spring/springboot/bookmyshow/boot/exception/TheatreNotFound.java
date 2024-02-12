package com.admindroid.spring.springboot.bookmyshow.boot.exception;

public class TheatreNotFound extends RuntimeException
{
	String message;

	public TheatreNotFound(String message)
	{

		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
