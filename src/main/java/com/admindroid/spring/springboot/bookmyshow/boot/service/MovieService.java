package com.admindroid.spring.springboot.bookmyshow.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admindroid.spring.springboot.bookmyshow.boot.dao.MovieDao;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Movie;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.MovieNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;



@Service
public class MovieService
{
	@Autowired
	MovieDao movieDao;
	
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
	
}
