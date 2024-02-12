package com.admindroid.spring.springboot.bookmyshow.boot.exception;

public class AdminNotFound extends RuntimeException
{
	String message;

	public AdminNotFound(String message) 
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}
}
