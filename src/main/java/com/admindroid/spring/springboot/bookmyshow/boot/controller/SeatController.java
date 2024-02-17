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

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Movie;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Seat;
import com.admindroid.spring.springboot.bookmyshow.boot.service.SeatService;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@RestController
@RequestMapping("/seat")
public class SeatController
{
	@Autowired
	SeatService seatService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Seat>> saveSeat(@RequestBody Seat seat)
	{
		return seatService.saveSeat(seat);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Seat>>  findSeat(@RequestParam int seatId)
	{
		return seatService.findSeat(seatId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Seat>> deleteSeat(@RequestParam int seatId)
	{
		return seatService.deleteSeat(seatId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Seat>> updateSeat(@RequestBody Seat seat,@RequestParam int seatId)
	{
		return seatService.updateSeat(seat, seatId);
	}
	
	
}
