package com.admindroid.spring.springboot.bookmyshow.boot.exception;

public class UserNotFound extends RuntimeException
{
	String message;

	public UserNotFound(String message) 
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}
	
}
