package com.admindroid.spring.springboot.bookmyshow.boot.exception;

public class TheatreAdminNotFound extends RuntimeException
{
	String message;

	public TheatreAdminNotFound(String message) 
	{
	
		this.message = message;
	}

	public String getMessage() 
	{
		return message;
	}
	
}
