package com.admindroid.spring.springboot.bookmyshow.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admindroid.spring.springboot.bookmyshow.boot.dao.TicketDao;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Ticket;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.TicketNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;
@Service
public class TicketService 
{
	@Autowired
	TicketDao ticketDao;
	
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(Ticket ticket)
	{
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		structure.setMessage("Ticket saved successfull");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(ticketDao.saveTicket(ticket));;
		return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> findTicket(int ticketId)
	{
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		
		Ticket tic=ticketDao.findTicket(ticketId);
		if(tic != null)
		{
			structure.setMessage("Ticket found.....");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(ticketDao.findTicket(ticketId));;
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.FOUND);
		}
		throw new TicketNotFound("Ticket  not found for given id "+ ticketId);
		
	}
	public ResponseEntity<ResponseStructure<Ticket>> deleteTicket(int ticketId)
	{
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		Ticket tic=ticketDao.findTicket(ticketId);
		if(tic != null)
		{
			structure.setMessage("Ticket removed");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(ticketDao.deleteTicket(ticketId));
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.OK);
		}
		throw new TicketNotFound("Theatre not found for given  id "+ ticketId);
		
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(Ticket ticket, int ticketId)
	{
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		Ticket tic=ticketDao.findTicket(ticketId);
		if(tic != null)
		{
			structure.setMessage("Ticket Updated Success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(ticketDao.updateTicket(ticket, ticketId));
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.OK);
		}
		throw new TicketNotFound("Ticket not found for given id "+ ticketId);
		
	}
	
	public ResponseEntity<ResponseStructure<List<Ticket>>> findAllTicket()
	{
		ResponseStructure<List<Ticket>> structure=new ResponseStructure<List<Ticket>>();
		List<Ticket> list=ticketDao.findAllTickets();
//		if(!list.isEmpty())
//		{
			structure.setMessage("List of movies: ");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(ticketDao.findAllTickets());
			return new ResponseEntity<ResponseStructure<List<Ticket>>>(structure, HttpStatus.FOUND);
//		}
//		throw 
		
	}
	
}
