package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Entity
@Component
@Getter
@Setter
public class Ticket
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketId;
	private ScreenNumber screenNumber;
	@Positive
	private int movieId;
	@NotNull(message = "movie name can not be null")
	@NotBlank(message = "movie name can not be blank")	
	private String movieName;
	@DateTimeFormat(pattern = "HH:mm:ss" )
	private LocalTime movieStartTime;
	@DateTimeFormat(pattern = "HH:mm:ss" )
	private LocalTime moviesEndTime;
	@NotNull(message = "movie language can not be null")
	@NotBlank(message = "movie language can not be blank")
	private String movieLanguage;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate bookingDate;
	@Positive
	private double totalTicketPrice;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Seat> ticketSeats;
	@OneToOne(cascade = CascadeType.ALL)
	private Payment ticketPayment;
}

 



