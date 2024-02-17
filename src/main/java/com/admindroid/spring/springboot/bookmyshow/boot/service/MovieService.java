package com.admindroid.spring.springboot.bookmyshow.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admindroid.spring.springboot.bookmyshow.boot.dao.MovieDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dao.SeatDao;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Movie;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Seat;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.SeatType;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.MovieNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.SeatNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.SeatRepo;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;



@Service
public class MovieService
{
	@Autowired
	MovieDao movieDao;
	
	@Autowired
	SeatDao seatDao;
	@Autowired
	SeatRepo seatRepo;
	
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(Movie movie)
	{
		ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		structure.setMessage("Movie saved successfull");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(movieDao.saveMovie(movie));;
		return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Movie>> findMovie(int movieId)
	{
		ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		
		Movie mov=movieDao.findMovie(movieId);
		if(mov != null)
		{
			structure.setMessage("Movie found.....");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(movieDao.findMovie(movieId));;
			return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.FOUND);
		}
		throw new MovieNotFound("movie not found for given id "+ movieId);
		
	}
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(int movieId)
	{
		ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		Movie mov=movieDao.findMovie(movieId);
		if(mov != null)
		{
			structure.setMessage("movie removed");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(movieDao.deleteMovie(movieId));
			return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.OK);
		}
		throw new MovieNotFound("movie not found for given  id "+ movieId);
		
	}
	
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(Movie movie, int movieId)
	{
		ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
		Movie mov=movieDao.findMovie(movieId);
		if(mov != null)
		{
			structure.setMessage("Movie Updated Success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(movieDao.updateMovie(movie, movieId));
			return new ResponseEntity<ResponseStructure<Movie>>(structure, HttpStatus.OK);
		}
		throw new MovieNotFound("Movie not found for given id "+ movieId);
		
	}
	
	public ResponseEntity<ResponseStructure<List<Movie>>> findAllMovie()
	{
		ResponseStructure<List<Movie>> structure=new ResponseStructure<List<Movie>>();
		List<Movie> list=movieDao.findAllMovies();
//		if(!list.isEmpty())
//		{
			structure.setMessage("List of movies: ");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(movieDao.findAllMovies());
			return new ResponseEntity<ResponseStructure<List<Movie>>>(structure, HttpStatus.FOUND);
//		}
//		throw 
		
	}
	public List<Seat> findSeatsAvailability(int movieId,SeatType seatType) {
		Movie movie=movieDao.findMovie(movieId);
		if(movie != null)
		{
		List<Seat> seatList=movie.getSeatList();
		List<Seat> seatsList=new ArrayList<Seat>();
		for (Seat seat : seatList) {
			if(seat.isSeatAvailability()==true && seat.getSeatType()==seatType) {
				seatsList.add(seat);
			}
		}
		return seatsList;
		}
		throw new  MovieNotFound("You cannot see the seat availability b'coz movie not found for given id.");
	}
	public  ResponseEntity<ResponseStructure<List<Seat>>> findSeatAvailability(int movieId,SeatType seatType) {
		Movie movie=movieDao.findMovie(movieId);
		if(movie != null)
		{
			List<Seat> seatList=movie.getSeatList();
			List<Seat> seatsList=new ArrayList<Seat>();
			for (Seat seat : seatList) {
				if(seat.isSeatAvailability()==true && seat.getSeatType()==seatType) {
					seatsList.add(seat);
				}
			}
			ResponseStructure<List<Seat>> structure=new ResponseStructure<List<Seat>>();
			structure.setMessage("find seat availability found success");
			structure.setStatus(HttpStatus .FOUND.value());
			structure.setData(seatsList);
			return new ResponseEntity<ResponseStructure<List<Seat>>>(structure,HttpStatus.FOUND);
		}
		throw new  MovieNotFound("You cannot see the seat availability b'coz movie not found for given id.");
		
	}
	public  ResponseEntity<ResponseStructure<Movie>> assignSeatsToMovies(int movieId,List<Integer> seatIds) {
		Movie movie=movieDao.findMovie(movieId);
		if(movie != null) {
		List<Seat> seats=seatRepo.findAllById(seatIds);
			if(seats != null) {
				movie.setSeatList(seats);
				ResponseStructure<Movie> structure=new ResponseStructure<Movie>();
				structure.setMessage("assign seats to Movie success");
				structure.setStatus(HttpStatus .OK.value());
				structure.setData(movieDao.updateMovie(movie,movie.getMovieId()));
				return new ResponseEntity<ResponseStructure<Movie>>(structure,HttpStatus.OK);
			}
			else {
				throw new SeatNotFound("we can't assign seats to movies because,seats not found for set of seatids");
			}
		}
		 throw new MovieNotFound("we can't assign seats to movies because,movie not found for the given id");
	}
	
}
