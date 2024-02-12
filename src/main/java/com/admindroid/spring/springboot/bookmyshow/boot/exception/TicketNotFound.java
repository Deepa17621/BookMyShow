package com.admindroid.spring.springboot.bookmyshow.boot.exception;

public class TicketNotFound extends RuntimeException
{
	String message;

	public TicketNotFound(String message) 
	{
		this.message = message;
	}

	public String getMessage() 
	{
		return message;
	}
	
	
}
