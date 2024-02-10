package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Seat
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seatId;
	private String seatNumber;
	private boolean seatAvailability;
	private SeatType seatType;
}
