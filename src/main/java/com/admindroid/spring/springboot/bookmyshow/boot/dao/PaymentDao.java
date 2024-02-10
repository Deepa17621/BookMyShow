package com.admindroid.spring.springboot.bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Payment;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.PaymentRepo;
@Repository
public class PaymentDao 
{
	@Autowired
	PaymentRepo paymentRepo;
	
	public Payment savePayment(Payment payment)
	{
		return paymentRepo.save(payment);
	}
	public Payment  findPayment(int paymentId)
	{
		Optional<Payment> opPayment=paymentRepo.findById(paymentId);
		if(opPayment.isPresent())
		{
			return opPayment.get();
		}
		return null;
	}
	
	public Payment deletePayment(int paymentId)
	{
		Payment payment=findPayment(paymentId);
		paymentRepo.delete(payment);
		
		return payment;
	}
	
	public Payment updatePayment(Payment payment, int paymentId)
	{
		Payment exPayment=findPayment(paymentId);
		if(exPayment != null)
		{
			payment.setPaymentId(paymentId);
			return paymentRepo.save(payment);
		}
		return null;
	}
	public List<Payment> findAllpayments()
	{
		return paymentRepo.findAll();
	}
}
