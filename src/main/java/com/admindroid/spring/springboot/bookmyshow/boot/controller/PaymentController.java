package com.admindroid.spring.springboot.bookmyshow.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Payment;
import com.admindroid.spring.springboot.bookmyshow.boot.service.PaymentService;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@RestController
@RequestMapping("/payment")
public class PaymentController
{
	@Autowired
	PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>> savePayment(@RequestBody Payment payment)
	{
		return paymentService.savePayment(payment);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Payment>>  findPayment(@RequestParam int paymentId)
	{
		return paymentService.findPayment(paymentId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Payment>> deletePayment(@RequestParam int paymentId)
	{
		return paymentService.deletePayment(paymentId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Payment>> updatePayment(@RequestBody Payment payment,@RequestParam int paymentId)
	{
		return paymentService.updatePayment(payment, paymentId);
	}
}
