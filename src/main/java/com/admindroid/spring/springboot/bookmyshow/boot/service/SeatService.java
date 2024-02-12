package com.admindroid.spring.springboot.bookmyshow.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.admindroid.spring.springboot.bookmyshow.boot.dao.SeatDao;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Seat;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.SeatNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@Service
public class SeatService
{
	@Autowired
	SeatDao seatDao;
	public ResponseEntity<ResponseStructure<Seat>> saveSeat(Seat seat)
	{
		ResponseStructure<Seat> structure=new ResponseStructure<Seat>();
		structure.setMessage("Seat saved successfull");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(seatDao.saveSeat(seat));;
		return new ResponseEntity<ResponseStructure<Seat>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Seat>> findSeat(int seatId)
	{
		ResponseStructure<Seat> structure=new ResponseStructure<Seat>();
		Seat sea=seatDao.findSeat(seatId);
		if(sea != null)
		{
			structure.setMessage("Seat found.....");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(seatDao.findSeat(seatId));;
			return new ResponseEntity<ResponseStructure<Seat>>(structure, HttpStatus.FOUND);
		}
		throw new SeatNotFound("Seat  not found for given id "+ seatId);
		
	}
	public ResponseEntity<ResponseStructure<Seat>> deleteSeat(int seatId)
	{
		ResponseStructure<Seat> structure=new ResponseStructure<Seat>();
		Seat sea=seatDao.findSeat(seatId);
		if(sea != null)
		{
			structure.setMessage("theatre removed");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(seatDao.deleteSeat(seatId));
			return new ResponseEntity<ResponseStructure<Seat>>(structure, HttpStatus.OK);
		}
		throw new SeatNotFound("Seat not found for given  id "+ seatId);
		
	}
	
	public ResponseEntity<ResponseStructure<Seat>> updateSeat(Seat seat, int seatId)
	{
		ResponseStructure<Seat> structure=new ResponseStructure<Seat>();
		Seat sea=seatDao.findSeat(seatId);
		if(sea != null)
		{
			structure.setMessage("Movie Updated Success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(seatDao.updateSeat(seat, seatId));
			return new ResponseEntity<ResponseStructure<Seat>>(structure, HttpStatus.OK);
		}
		throw new SeatNotFound("Seat not found for given id "+ seatId);
		
	}
	
	public ResponseEntity<ResponseStructure<List<Seat>>> findAllSeat()
	{
		ResponseStructure<List<Seat>> structure=new ResponseStructure<List<Seat>>();
		List<Seat> list=seatDao.findAllSeats();
//		if(!list.isEmpty())
//		{
			structure.setMessage("List of movies: ");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(seatDao.findAllSeats());
			return new ResponseEntity<ResponseStructure<List<Seat>>>(structure, HttpStatus.FOUND);
//		}
//		throw 
		
	}
	
}
