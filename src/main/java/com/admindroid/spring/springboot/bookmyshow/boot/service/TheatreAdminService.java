package com.admindroid.spring.springboot.bookmyshow.boot.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admindroid.spring.springboot.bookmyshow.boot.dao.TheatreAdminDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dao.TheatreDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dto.TheatreAdminDto;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Theatre;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.TheatreAdmin;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.EmailWrongException;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.PasswordWrongException;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.TheatreAdminNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.TheatreNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@Service
public class TheatreAdminService 
{
	@Autowired
	TheatreAdminDao theatreAdminDao;
	@Autowired
	TheatreDao tDao;
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> saveTheatreAdmin(TheatreAdmin theatreAdmin) 
	{
		TheatreAdminDto dto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(theatreAdminDao.saveTheatreAdmin(theatreAdmin), dto);
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		structure.setMessage("TheatreAdmin saved success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure, HttpStatus.CREATED);
	}
	
	//Find Admin Detail 
	public ResponseEntity<ResponseStructure<TheatreAdminDto>>  findTheatreAdmin(int theatreAdminId)
	{
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		TheatreAdmin theatreAdmin=theatreAdminDao.findTheatreAdmin(theatreAdminId);
		TheatreAdminDto dto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(theatreAdmin, dto);
		if(theatreAdmin != null)
		{
			structure.setMessage("Theatre Admin found........");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure, HttpStatus.FOUND);
		}
		throw new TheatreAdminNotFound("Theatre Admin not found for given id "+theatreAdminId);
		
		
	}
	//Delete Admin Details
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> deleteTheatreAdmin(int theatreAdminId)
	{
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		
		TheatreAdmin theatreAdmin=theatreAdminDao.findTheatreAdmin(theatreAdminId);
		
		if(theatreAdmin != null)
		{
		TheatreAdmin delTheatreAdmin=theatreAdminDao.deleteTheatreAdmin(theatreAdminId);
		TheatreAdminDto dto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(delTheatreAdmin, dto);
		structure.setMessage("Theatre Admin deleted........");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure, HttpStatus.OK);
		}
		throw new TheatreAdminNotFound("Theatre Admin not found for given id "+theatreAdminId);
		
	}
	//Update Student details
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> updateTheatreAdmin(TheatreAdmin theatreAdmin, int theatreAdminId)
	{
		ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
		
		TheatreAdmin adm=theatreAdminDao.findTheatreAdmin(theatreAdminId);
		if(adm != null)
		{
			TheatreAdmin updateAdm=theatreAdminDao.updateTheatreAdmin(theatreAdmin, theatreAdminId);
			TheatreAdminDto dto=new TheatreAdminDto();
			ModelMapper mapper=new ModelMapper();
			mapper.map(updateAdm, dto);
			structure.setMessage("Theatre Admin deleted........");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure, HttpStatus.OK);
		}
		
		throw new TheatreAdminNotFound(" Theatre Admin not found for given id "+theatreAdminId);
	}
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> findByEmail(String theatreAdminEmail,String theatreAdminPassword){
		TheatreAdminDto aDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		TheatreAdmin tadmin=theatreAdminDao.findByEmail(theatreAdminEmail);
		if(tadmin.getTheatreAdminEmail().equals(theatreAdminEmail)) {
			if(tadmin.getTheatreAdminPassword().equals(theatreAdminPassword)) {
				mapper.map(tadmin, aDto);
				ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
				structure.setData(aDto);
				structure.setMessage("theatre Admin login success");
				structure.setStatus(HttpStatus.FOUND.value());
				return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.FOUND);
			}
			throw new PasswordWrongException("theatre Admin Password is wrong");
		}
		throw new EmailWrongException("theatre admin email is wrong");
	}

	
	public ResponseEntity<ResponseStructure<TheatreAdminDto>> assignTheatreToTheatreAdmin(int theatreAdminId,int theatreId){
		TheatreAdminDto taDto=new TheatreAdminDto();
		ModelMapper mapper=new ModelMapper();
		TheatreAdmin tAdmin=theatreAdminDao.findTheatreAdmin(theatreAdminId);
		Theatre theatre=tDao.findTheatre(theatreId);
		if(tAdmin != null)
		{
			if(theatre != null)
			{
				tAdmin.setTheatre(theatre);
				mapper.map(tDao.updateTheatre(theatre, theatreId), taDto);
				ResponseStructure<TheatreAdminDto> structure=new ResponseStructure<TheatreAdminDto>();
				structure.setMessage("assign theatre to Theatre Admin success");
				structure.setStatus(HttpStatus .OK.value());
				structure.setData(taDto);
				return new ResponseEntity<ResponseStructure<TheatreAdminDto>>(structure,HttpStatus.OK);
			}
			else 
			{
				throw new TheatreNotFound("theatre not assigned to the theatre admin because,theatre not found for the given id");
			}
			
		}
		throw new TheatreAdminNotFound("we can't assign theatre to theatre admin because,theatre Admin not found for the given id");
	}

}
