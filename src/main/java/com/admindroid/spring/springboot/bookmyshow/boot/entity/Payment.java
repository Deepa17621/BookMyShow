package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Entity
@Component
@Getter
@Setter
public class Payment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	private LocalDate paymentDate;
	@Positive
	private double price;
	@NotBlank(message = "payment method cannot be blank")
	@NotNull(message = "payment method cannot be null")
	private String paymentMethod;
}
