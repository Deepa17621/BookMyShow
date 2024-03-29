package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import java.util.List;


import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
public class Admin
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;
	@NotNull(message="admin name cannot be null.")
	@NotBlank(message ="admin name cannot be blank.")
	private String adminName;
	@Positive
	@Min(value=6000000000l, message = "contact number should contain 10 digits")
	@Max(value=9999999999l, message = "contact numer should contain 10 digits")
	private long adminContact;
	@NotBlank(message = "email cannot be blank")
	@NotNull(message="email cannot be null")
	@Email
	private String adminMail;
	@NotNull(message="password cannot be null")
	@NotBlank(message = "password cannot be blank")
	@Size(min=8, message = "password must contain minimum 8 characters.")
	@Pattern(regexp = "^(?=.*[a-z]) (?=.*[A-Z]) (?=.*\\d) (?=.*[@#$%^&+!]).{8,}$",
	message = "password must have at least 1 digit, 1 uppercase, 1 lowercase and 1 special character.")
	private String adminPassword;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Theatre> theatreList;
	
}
