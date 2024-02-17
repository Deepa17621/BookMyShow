package com.admindroid.spring.springboot.bookmyshow.boot.controller;

import java.util.List;

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


import com.admindroid.spring.springboot.bookmyshow.boot.entity.Movie;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Seat;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.SeatType;
import com.admindroid.spring.springboot.bookmyshow.boot.service.MovieService;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@RestController
@RequestMapping("/movie")
public class MovieController 
{
	@Autowired
	MovieService movieService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Movie>> saveMovie(@RequestBody Movie movie)
	{
		return movieService.saveMovie(movie);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Movie>>  findMovie(@RequestParam int movieId)
	{
		return movieService.findMovie(movieId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Movie>> deleteMovie(@RequestParam int movieId)
	{
		return movieService.deleteMovie(movieId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Movie>> updateMovie(@RequestBody Movie movie,@RequestParam int movieId)
	{
		return movieService.updateMovie(movie, movieId);
	}
	
	@PutMapping("assignSeatsToMovies")
	public  ResponseEntity<ResponseStructure<Movie>> assignSeatsToMovies(@RequestParam int movieId,@RequestBody List<Integer> seatIds) {
		return movieService.assignSeatsToMovies(movieId, seatIds);
	}
	
	@GetMapping("findSeatAvailability")
	ResponseEntity<ResponseStructure<List<Seat>>> findSeatAvailability(@RequestParam int movieId,@RequestParam SeatType seatType) {
		return movieService.findSeatAvailability(movieId, seatType);
	}
	
}
