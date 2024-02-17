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


import com.admindroid.spring.springboot.bookmyshow.boot.dto.TheatreAdminDto;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.TheatreAdmin;
import com.admindroid.spring.springboot.bookmyshow.boot.service.TheatreAdminService;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;
@RestController
@RequestMapping("theatreAdmin")
public class TheatreAdminController
{
	@Autowired
	TheatreAdminService theatreAdminService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> saveTheatreAdmin(@RequestBody TheatreAdmin theatreAdmin)
	{
		return theatreAdminService.saveTheatreAdmin(theatreAdmin);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>>  findTheatreAdmin(@RequestParam int theatreAdminId)
	{
		return theatreAdminService.findTheatreAdmin(theatreAdminId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteTheatreAdmin(@RequestParam int theatreAdminId)
	{
		return theatreAdminService.deleteTheatreAdmin(theatreAdminId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> updateTheatreAdmin(@RequestBody TheatreAdmin theatreAdmin,@RequestParam int theatreAdminId)
	{
		return theatreAdminService.updateTheatreAdmin(theatreAdmin, theatreAdminId);
	}
	
	@PutMapping("assignTheatreToTheatreAdmin")
	ResponseEntity<ResponseStructure<TheatreAdminDto>> assignTheatreToTheatreAdmin(@RequestParam int  theatreAdminId,@RequestParam int  theatreId){
		return theatreAdminService.assignTheatreToTheatreAdmin(theatreAdminId, theatreId);
	}
}
