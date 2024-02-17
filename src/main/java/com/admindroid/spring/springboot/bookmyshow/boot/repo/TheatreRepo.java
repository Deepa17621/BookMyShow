package com.admindroid.spring.springboot.bookmyshow.boot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Seat;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.SeatType;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Integer>
{
	public List<Seat>  findaByTheatreIdAndSeatTypeAndSeatAvailability(int theatreId, SeatType seatType); 
}
