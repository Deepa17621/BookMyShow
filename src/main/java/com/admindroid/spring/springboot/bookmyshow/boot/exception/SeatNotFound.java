package com.admindroid.spring.springboot.bookmyshow.boot.exception;

public class SeatNotFound extends RuntimeException
{
	String message;

	public SeatNotFound(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}
	
	
}
