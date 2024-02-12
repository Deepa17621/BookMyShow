package com.admindroid.spring.springboot.bookmyshow.boot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;



@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler
{ 
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminNotFoundException(AdminNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("Admin Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> userNotFoundException(UserNotFound ex)
	{
		ResponseStructure<String> structure=new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setMessage("User Does Not Exist");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> movieNotFoundException(MovieNotFound ex)
	{
		ResponseStructure<String> structure =new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Movie Does not exist");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> theatreNotFoundException(TheatreNotFound ex)
	{
		ResponseStructure<String> structure =new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Theatre Does not exist");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> seatNotFoundException(SeatNotFound ex)
	{
		ResponseStructure<String> structure =new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Seat Does not exist");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> ticketNotFoundException(TicketNotFound ex)
	{
		ResponseStructure<String> structure =new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Ticket Does not exist");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> paymentNotFoundException(PaymentNotFound ex)
	{
		ResponseStructure<String> structure =new ResponseStructure<String>();
		structure.setData(ex.getMessage());
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage("Payment Does not exist");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}
}
