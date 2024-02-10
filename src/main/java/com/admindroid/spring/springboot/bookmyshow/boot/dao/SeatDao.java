package com.admindroid.spring.springboot.bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Seat;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.SeatRepo;

@Repository
public class SeatDao
{
	@Autowired
	SeatRepo seatRepo;
	public Seat saveMovie(Seat seat)
	{
		return seatRepo.save(seat);
	}
	public Seat  findSeat(int seatId)
	{
		Optional<Seat> opSeat=seatRepo.findById(seatId);
		if(opSeat.isPresent())
		{
			return opSeat.get();
		}
		return null;
	}
	
	public Seat deleteSeat(int seatId)
	{
		Seat seat=findSeat(seatId);
		seatRepo.delete(seat);
		
		return seat;
	}
	
	public Seat updateSeat(Seat seat, int seatId)
	{
		Seat exSeat=findSeat(seatId);
		if(exSeat != null)
		{
			seat.setSeatId(seatId);
			return seatRepo.save(seat);
		}
		return null;
	}
	public List<Seat> findAllseats()
	{
		return seatRepo.findAll();
	}
}
