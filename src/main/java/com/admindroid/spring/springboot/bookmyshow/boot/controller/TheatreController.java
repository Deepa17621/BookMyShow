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


import com.admindroid.spring.springboot.bookmyshow.boot.entity.Theatre;
import com.admindroid.spring.springboot.bookmyshow.boot.service.TheatreService;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@RestController
@RequestMapping("/theatre")
public class TheatreController
{
	@Autowired
	TheatreService theatreService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Theatre>> saveTheatre(@RequestBody Theatre theatre)
	{
		return theatreService.saveTheatre(theatre);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<Theatre>>  findTheatre(@RequestParam int theatreId)
	{
		return theatreService.findTheatre(theatreId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Theatre>> deleteTheatre(@RequestParam int theatreId)
	{
		return theatreService.deleteTheatre(theatreId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Theatre>> updateTheatre(@RequestBody Theatre theatre,@RequestParam int theatreId)
	{
		return theatreService.updateTheatre(theatre, theatreId);
	}
	
	@PutMapping("assignScreensToTheatre")
	public ResponseEntity<ResponseStructure<Theatre>> assignScreensToTheatre(@RequestParam int theatreId,@RequestBody List<Integer> screenIds) {
		return theatreService.assignScreensToTheatre(theatreId, screenIds);
	}
}
