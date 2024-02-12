package com.admindroid.spring.springboot.bookmyshow.boot.exception;

public class MovieNotFound extends RuntimeException
{
	String message;

	public MovieNotFound(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}
	
	
	
}
