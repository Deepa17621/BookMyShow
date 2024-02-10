package com.admindroid.spring.springboot.bookmyshow.boot.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
@Entity
@Component
@Getter
@Setter
public class Payment 
{
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	private LocalDate paymentDat;
	private double price;
	private String paymentMethod;
}
