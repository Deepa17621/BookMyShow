package com.admindroid.spring.springboot.bookmyshow.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admindroid.spring.springboot.bookmyshow.boot.dao.TheatreDao;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Movie;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Theatre;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.TheatreNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.MovieRepo;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.TheatreRepo;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@Service
public class TheatreService 
{
	@Autowired
	TheatreDao theatreDao;
	@Autowired
	TheatreRepo tRepo;
	@Autowired
	MovieRepo mRepo;
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(Theatre theatre)
	{
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		structure.setMessage("Theatre saved successfull");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(theatreDao.saveTheatre(theatre));;
		return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> findTheatre(int theatreId)
	{
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		
		Theatre the=theatreDao.findTheatre(theatreId);
		if(the != null)
		{
			structure.setMessage("Theatre found.....");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(theatreDao.findTheatre(theatreId));;
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.FOUND);
		}
		throw new TheatreNotFound("theatre  not found for given id "+ theatreId);
		
	}
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(int theatreId)
	{
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		Theatre the=theatreDao.findTheatre(theatreId);
		if(the != null)
		{
			structure.setMessage("theatre removed");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(theatreDao.deleteTheatre(theatreId));
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.OK);
		}
		throw new TheatreNotFound("Theatre not found for given  id "+ theatreId);
		
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(Theatre theatre, int theatreId)
	{
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		Theatre the=theatreDao.findTheatre(theatreId);
		if(the != null)
		{
			structure.setMessage("Movie Updated Success");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(theatreDao.updateTheatre(theatre, theatreId));
			return new ResponseEntity<ResponseStructure<Theatre>>(structure, HttpStatus.OK);
		}
		throw new TheatreNotFound("Theatre not found for given id "+ theatreId);
		
	}
	
	public ResponseEntity<ResponseStructure<List<Theatre>>> findAllTheatre()
	{
		ResponseStructure<List<Theatre>> structure=new ResponseStructure<List<Theatre>>();
		List<Theatre> list=theatreDao.findAllTheatres();
//		if(!list.isEmpty())
//		{
			structure.setMessage("List of movies: ");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(theatreDao.findAllTheatres());
			return new ResponseEntity<ResponseStructure<List<Theatre>>>(structure, HttpStatus.FOUND);
//		}
//		throw 
		
	}
	
	public ResponseEntity<ResponseStructure<Theatre>> assignMoviesToTheatre(int theatreId,List<Integer> movieIds) {
		Theatre theatre=theatreDao.findTheatre(theatreId);
		if(theatre != null) {
		List<Movie> exmovies=mRepo.findAllById(movieIds);
		theatre.setTheatreMovieList(exmovies);
		ResponseStructure<Theatre> structure=new ResponseStructure<Theatre>();
		structure.setMessage("assign movies to theatre success");
		structure.setStatus(HttpStatus .OK.value());
		structure.setData(theatreDao.updateTheatre(theatre, theatreId));
		return new ResponseEntity<ResponseStructure<Theatre>>(structure,HttpStatus.OK);
		}
		throw new TheatreNotFound("we can't assign movie to the theatre because, theatre not found for the given id");
	}
}
