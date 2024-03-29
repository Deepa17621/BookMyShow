package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Entity
@Component
@Getter
@Setter
public class Theatre 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreId;
	
	@NotBlank(message = "theatre name cannnot be blank")
	@NotNull(message = "theatre name cannot be null")
	private String  theatreName;
	@Positive
	@Max(value = 10)
	private int totalNoOfSeats;
	@NotNull(message = "theatre Location can not be null")
	@NotBlank(message = "theatre Location can not be blank")
	private String theatreLocation;
	@OneToMany
	List<Screen> theatreScreensList;
	
	
}
