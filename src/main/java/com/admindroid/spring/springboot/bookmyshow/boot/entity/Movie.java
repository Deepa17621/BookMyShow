package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class Movie
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int movieId;
	private String movieName;
	private LocalDate movieReleaseDate;
	private LocalTime movieStartTime;
	private LocalTime movieEndTime;
	private String movieLanguage;
}
