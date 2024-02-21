package com.admindroid.spring.springboot.bookmyshow.boot.entity;


import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Component
public class Screen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int screenId;
	private ScreenNumber screenNumber;
	@NotNull(message = "screen name can not be null")
	@NotBlank(message = "screen name can not be blank")
	private String screenName;
	@OneToMany
	private List<Movie> moviesList;
	
	
	
	
}
