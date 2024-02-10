package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private String  theatreName;
	private String theatreLocation;
	@OneToMany
	List<Movie> theatreMovieList;
}
