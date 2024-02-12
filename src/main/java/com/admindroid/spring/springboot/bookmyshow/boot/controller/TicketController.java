package com.admindroid.spring.springboot.bookmyshow.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Ticket;
import com.admindroid.spring.springboot.bookmyshow.boot.service.TicketService;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@RestController
@RequestMapping("/ticket")
public class TicketController
{
	@Autowired
	TicketService ticketService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(@RequestBody Ticket ticket)
	{
		return ticketService.saveTicket(ticket);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Ticket>>  findTicket(@RequestParam int ticketId)
	{
		return ticketService.findTicket(ticketId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Ticket>> deleteTicket(@RequestParam int ticketId)
	{
		return ticketService.deleteTicket(ticketId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(@RequestBody Ticket ticket,@RequestParam int ticketId)
	{
		return ticketService.updateTicket(ticket, ticketId);
	}
}
