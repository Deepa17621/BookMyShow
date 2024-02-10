package com.admindroid.spring.springboot.bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.admindroid.spring.springboot.bookmyshow.boot.entity.Theatre;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.TheatreRepo;

@Repository
public class TheatreDao 
{
	@Autowired
	TheatreRepo theatreRepo;
	public Theatre saveTheatre(Theatre theatre)
	{
		return theatreRepo.save(theatre);
	}
	public Theatre  findTheatre(int theatreId)
	{
		Optional<Theatre> opTheatre=theatreRepo.findById(theatreId);
		if(opTheatre.isPresent())
		{
			return opTheatre.get();
		}
		return null;
	}
	
	public Theatre deleteTheatre(int theatreId)
	{
		Theatre theatre=findTheatre(theatreId);
		theatreRepo.delete(theatre);
		
		return theatre;
	}
	
	public Theatre updateTheatre(Theatre theatre, int theatreId)
	{
		Theatre exTheatre=findTheatre(theatreId);
		if(exTheatre != null)
		{
			theatre.setTheatreId(theatreId);
			return theatreRepo.save(theatre);
		}
		return null;
	}
	public List<Theatre> findAlltheatres()
	{
		return theatreRepo.findAll();
	}
}
