package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotNull(message = "seat number can not be null")
	@NotBlank(message = "seat number can not be blank")
	private String seatNumber;
	@NotNull(message = "seat availability can not be null")
	private boolean seatAvailability;
	private SeatType seatType;
}
