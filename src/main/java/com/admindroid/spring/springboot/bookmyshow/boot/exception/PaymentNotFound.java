package com.admindroid.spring.springboot.bookmyshow.boot.exception;

public class PaymentNotFound extends RuntimeException
{
	String message;

	public PaymentNotFound(String message) 
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}
	
}
