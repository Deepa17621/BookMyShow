package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class TheatreAdmin 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int theatreAdminId;
	@NotNull(message = "theatre admin name cannot be null")
	@NotBlank(message = "theatre admin name cannot be blank")
	private String theatreAdminName;
	@Positive
	@Min(value = 6000000000l, message="contact num should contain 10 digits")
	@Max(value = 9999999999l, message="contact num should contain 10 digits")
	private long theatreAdminContact;
	@Email
	private String theatreAdminEmail;
	@NotNull(message="password cannot be null")
	@NotBlank(message = "password cannot be blank")
	@Size(min=8, message = "password must contain minimum 8 characters.")
	@Pattern(regexp = "^(?=.*[a-z]) (?=.*[A-Z]) (?=.*\\d) (?=.*[@#$%^&+!]).{8,}$",
	message = "password must have at least 1 digit, 1 uppercase, 1 lowercase and 1 special character.")
	private String theatreAdminPassword;
	@OneToOne(cascade = CascadeType.ALL)
	private Theatre theatre;
}
