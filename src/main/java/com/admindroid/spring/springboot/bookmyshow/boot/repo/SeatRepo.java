package com.admindroid.spring.springboot.bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Integer>
{

}
