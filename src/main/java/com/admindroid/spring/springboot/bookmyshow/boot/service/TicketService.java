package com.admindroid.spring.springboot.bookmyshow.boot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admindroid.spring.springboot.bookmyshow.boot.dao.MovieDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dao.PaymentDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dao.ScreenDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dao.SeatDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dao.TicketDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dao.UserDao;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Movie;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Payment;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.PaymentType;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Seat;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.SeatType;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Ticket;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.User;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.SeatNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.TicketNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.UserNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.SeatRepo;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.UserRepo;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;
@Service
public class TicketService 
{
	@Autowired
	TicketDao ticketDao;
	
	@Autowired
	UserDao uDao;
	
	@Autowired
	SeatDao sDao;
	@Autowired
	MovieService mService;
	@Autowired
	SeatRepo sRepo;
	@Autowired
	PaymentDao pDao;
	@Autowired
	ScreenDao screenDao;
	@Autowired
	MovieDao mDao;
	
	
	
	public ResponseEntity<ResponseStructure<Ticket>> saveTicket(Ticket ticket)
	{
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		structure.setMessage("Ticket saved successfull");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(ticketDao.saveTicket(ticket));;
		return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> findTicket(int ticketId)
	{
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		
		Ticket tic=ticketDao.findTicket(ticketId);
		if(tic != null)
		{
			structure.setMessage("Ticket found.....");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(ticketDao.findTicket(ticketId));;
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.FOUND);
		}
		throw new TicketNotFound("Ticket  not found for given id "+ ticketId);
		
	}
	public ResponseEntity<ResponseStructure<Ticket>> deleteTicket(int ticketId)
	{
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		Ticket tic=ticketDao.findTicket(ticketId);
		if(tic != null)
		{
			structure.setMessage("Ticket removed");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(ticketDao.deleteTicket(ticketId));
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.OK);
		}
		throw new TicketNotFound("Theatre not found for given  id "+ ticketId);
		
	}
	
	public ResponseEntity<ResponseStructure<Ticket>> updateTicket(Ticket ticket, int ticketId)
	{
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		Ticket tic=ticketDao.findTicket(ticketId);
		if(tic != null)
		{
			structure.setMessage("Ticket Updated Success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(ticketDao.updateTicket(ticket, ticketId));
			return new ResponseEntity<ResponseStructure<Ticket>>(structure, HttpStatus.OK);
		}
		throw new TicketNotFound("Ticket not found for given id "+ ticketId);
		
	}
	
	public ResponseEntity<ResponseStructure<List<Ticket>>> findAllTicket()
	{
		ResponseStructure<List<Ticket>> structure=new ResponseStructure<List<Ticket>>();
		List<Ticket> list=ticketDao.findAllTickets();
//		if(!list.isEmpty())
//		{
			structure.setMessage("List of movies: ");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(ticketDao.findAllTickets());
			return new ResponseEntity<ResponseStructure<List<Ticket>>>(structure, HttpStatus.FOUND);
//		}
//		throw 
		
	}
	
//	public ResponseEntity<ResponseStructure<Ticket>> () {
//		
//	}
	
	public List<Seat> bookSeat(List<Seat> availableSeats, List<Integer> seatIds, int movieId){
		List<Seat> seats=new ArrayList<Seat>();
		for (Seat seatAvail : availableSeats) {
			for (Integer integer : seatIds) {
				if(seatAvail.getSeatId()==integer) {
					seats.add(seatAvail);
					Movie movie=mDao.findMovie(movieId);
					movie.setTotalNoSeatsAvailable(movie.getTotalNoSeatsAvailable()-1);
					mDao.updateMovie(movie, movieId);
					seatAvail.setSeatAvailability(false);
					sDao.updateSeat(seatAvail, seatAvail.getSeatId());
				}
			}
		}
		return seats;
	}
	public ResponseEntity<ResponseStructure<Ticket>> ticketBooking(String userEmail,String userPassword,int movieId,SeatType seatType,List<Integer> seatIds,LocalDate bookingDate,PaymentType paymenType){
		User user=userLogin(userEmail, userPassword);
		if(user != null) {
		Ticket ticket=new Ticket();
		List<Seat> availableSeat=mService.findSeatsAvailability(movieId, seatType);
		if(availableSeat != null) {
		List<Seat> bookedSeats=bookSeat(availableSeat, seatIds,movieId);
		if(!bookedSeats.isEmpty()) {
		Payment payment= processPayement(bookedSeats,bookingDate,paymenType);
		ticket.setBookingDate(bookingDate);
		Movie movie=mDao.findMovie(movieId);
		ticket.setMovieId(movieId);
		ticket.setMovieLanguage(movie.getMovieLanguage());
		ticket.setMovieName(movie.getMovieName());
		ticket.setMovieStartTime(movie.getMovieStartTime());
		ticket.setMoviesEndTime(movie.getMovieEndTime());
		ticket.setTicketPayment(payment);
		ticket.setTicketSeats(bookedSeats);
		ticket.setTotalTicketPrice(payment.getPrice());
		Ticket newTicket=ticketDao.saveTicket(ticket);
		user.setTicket(newTicket);
		uDao.updateUser(user, user.getUserId());
		ResponseStructure<Ticket> structure=new ResponseStructure<Ticket>();
		structure.setMessage("ticket booked successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(newTicket);
		return new ResponseEntity<ResponseStructure<Ticket>>(structure,HttpStatus.CREATED);
		}
		throw new  SeatNotFound("user seats are not available please enter available seat Ids");

		}
		throw new  SeatNotFound("user seats are not available please enter available seat Ids");
		}
		throw new UserNotFound("user login required please provide correct credentials");
	}
	public ResponseEntity<ResponseStructure<Payment>> cancelBooking(int ticketId,String userEmail,String userPassword){
		User user=userLogin(userEmail, userPassword);
		if(user != null) {
		Ticket ticket=ticketDao.findTicket(ticketId);
		if(ticket != null) {
			List<Seat> lists = ticket.getTicketSeats();
			for (Seat seat : lists) {
				seat.setSeatAvailability(true);
				sDao.updateSeat(seat, seat.getSeatId());
				Movie movie=mDao.findMovie(ticket.getMovieId());
				movie.setTotalNoSeatsAvailable(movie.getTotalNoSeatsAvailable()+1);
				mDao.updateMovie(movie, ticket.getMovieId());
			}
			ticket.setTicketSeats(null);
			user.setTicket(null);
			uDao.updateUser(user, user.getUserId());
			Payment payment=ticket.getTicketPayment();
			ticketDao.deleteTicket(ticketId);
			ResponseStructure<Payment> structure=new ResponseStructure<Payment>();
			structure.setMessage("cancel booking success.amount refunded");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(payment);
			return new ResponseEntity<ResponseStructure<Payment>> (structure,HttpStatus.OK);
		}
		else {
			throw new TicketNotFound("ticket not found for given id");
		}
		}
		throw new UserNotFound("loginrequired please provide correct details");
	}
	private User userLogin(String userEmail, String userPassword) {
		User user=uDao.findByEmail(userEmail);
		if(user != null) {
		  if(user.getUserPassword().equals(userPassword)) {
			  return user;
		  }
		  return null;
		}
		return null;
	}
	private Payment processPayement(List<Seat> bookedSeats,LocalDate bookingDate,PaymentType paymentType) {
		Payment payment=new Payment();
		long amount=0;
		for (Seat seat : bookedSeats) {
			if(seat.getSeatType()==SeatType.premium) {
				amount+=150;
			}
			else if(seat.getSeatType()==SeatType.vip) {
				amount+=110;
			}
			else {
				amount+=60;
			}
		}
		payment.setPaymentDate(bookingDate);
		payment.setPaymentType(paymentType);
		payment.setPrice(amount);
		Payment newPayment=pDao.savePayment(payment);
		return newPayment;
	}
	
	
}
