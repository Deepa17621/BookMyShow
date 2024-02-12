package com.admindroid.spring.springboot.bookmyshow.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admindroid.spring.springboot.bookmyshow.boot.dao.PaymentDao;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Payment;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.PaymentNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;
@Service
public class PaymentService 
{
	@Autowired
	PaymentDao paymentDao;
	public ResponseEntity<ResponseStructure<Payment>> savePayment(Payment payment)
	{
		ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
		structure.setMessage("Payment saved successfull");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(paymentDao.savePayment(payment));;
		return new ResponseEntity<ResponseStructure<Payment>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Payment>> findPayment(int paymentId)
	{
		ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
		
		Payment pay=paymentDao.findPayment(paymentId);
		if(pay != null)
		{
			structure.setMessage("Payment found.....");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(paymentDao.findPayment(paymentId));;
			return new ResponseEntity<ResponseStructure<Payment>>(structure, HttpStatus.FOUND);
		}
		throw new PaymentNotFound("Payment  not found for given id "+ paymentId);
		
	}
	public ResponseEntity<ResponseStructure<Payment>> deletePayment(int paymentId)
	{
		ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
		Payment pay=paymentDao.findPayment(paymentId);
		if(pay != null)
		{
			structure.setMessage("Payment removed");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(paymentDao.deletePayment(paymentId));
			return new ResponseEntity<ResponseStructure<Payment>>(structure, HttpStatus.OK);
		}
		throw new PaymentNotFound("Payment not found for given  id "+ paymentId);
		
	}
	
	public ResponseEntity<ResponseStructure<Payment>> updatePayment(Payment payment, int paymentId)
	{
		ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
		Payment pay=paymentDao.findPayment(paymentId);
		if(pay != null)
		{
			structure.setMessage("Payment Updated Success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(paymentDao.updatePayment(payment, paymentId));
			return new ResponseEntity<ResponseStructure<Payment>>(structure, HttpStatus.OK);
		}
		throw new PaymentNotFound("Payment not found for given id "+ paymentId);
		
	}
	
//	public ResponseEntity<ResponseStructure<List<Theatre>>> findAllTheatre()
//	{
//		ResponseStructure<List<Theatre>> structure=new ResponseStructure<List<Theatre>>();
//		List<Theatre> list=theatreDao.findAllTheatres();
////		if(!list.isEmpty())
////		{
//			structure.setMessage("List of movies: ");
//			structure.setStatus(HttpStatus.FOUND.value());
//			structure.setData(theatreDao.findAllTheatres());
//			return new ResponseEntity<ResponseStructure<List<Theatre>>>(structure, HttpStatus.FOUND);
////		}
////		throw 
//		
//	}
}
