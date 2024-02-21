package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Entity
@Component
@Getter
@Setter
public class Movie
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	@NotBlank(message = "movie name cannot be blank")
	@NotBlank(message = "movie name cannot be null")
	private String movieName;
	@Positive
	private int totalNoSeatsAvailable;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate movieReleaseDate;
	@DateTimeFormat(pattern = "HH:mm:ss" )
	private LocalTime movieStartTime;
	@DateTimeFormat(pattern = "HH:mm:ss" )
	private LocalTime movieEndTime;
	@NotBlank(message = "movie language cannot be blank")
	@NotNull(message = "movie language cannot be null")
	private String movieLanguage;
	@OneToMany
	private List<Seat> seatList;
}
