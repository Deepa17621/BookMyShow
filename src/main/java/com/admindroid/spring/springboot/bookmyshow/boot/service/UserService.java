package com.admindroid.spring.springboot.bookmyshow.boot.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admindroid.spring.springboot.bookmyshow.boot.dao.UserDao;

import com.admindroid.spring.springboot.bookmyshow.boot.dto.UserDto;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.User;

import com.admindroid.spring.springboot.bookmyshow.boot.exception.UserNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.UserRepo;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@Service
public class UserService
{
	@Autowired
	UserDao userDao;
	@Autowired
	UserRepo userRepo;
	
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(User user) 
	{
		UserDto dto=new UserDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(userDao.saveUser(user), dto);
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		structure.setMessage("User saved success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.CREATED);
	}
	
	//Find Admin Detail 
	public ResponseEntity<ResponseStructure<UserDto>>  findUser(int userId)
	{
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		User user=userDao.findUser(userId);
		UserDto dto=new UserDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(user, dto);
		if(user != null)
		{
			structure.setMessage("Admin found........");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.FOUND);
		}
		throw new UserNotFound("User not found for given id "+userId);
		
		
	}
	
	//Find by User Email
	public ResponseEntity<ResponseStructure<UserDto>> findByEmail(String userEmail,String userPassword){
		UserDto uDto=new UserDto();
		ModelMapper mapper=new ModelMapper();
		User user=userDao.findByEmail(userEmail);
		System.out.println(user);
		if(user.getUserMail().equals(userEmail)) {
			if(user.getUserPassword().equals(userPassword)) {
		mapper.map(user, uDto);
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		structure.setMessage("User login success");
		structure.setStatus(HttpStatus .FOUND.value());
		structure.setData(uDto);
		return new ResponseEntity<ResponseStructure<UserDto>> (structure,HttpStatus.FOUND);
			}
			else {
				throw new UserNotFound(" your password is wrong please provide correct deteils");
			}
		}
			else {
				throw new UserNotFound(" your email is wrong please provide correct deteils");
			}
	}
	//Delete Admin Details
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(int userId)
	{
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		
		User user=userDao.findUser(userId);
		
		if(user != null)
		{
		User delUser=userDao.deleteUser(userId);
		UserDto dto=new UserDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(delUser, dto);
		structure.setMessage("User deleted........");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.OK);
		}
		throw new UserNotFound("User not found for given id "+userId);
		
	}
	//Update Student details
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(User user, int userId)
	{
		ResponseStructure<UserDto> structure=new ResponseStructure<UserDto>();
		
		User use=userDao.findUser(userId);
		if(use != null)
		{
			User updateUse=userDao.updateUser(user, userId);
			UserDto dto=new UserDto();
			ModelMapper mapper=new ModelMapper();
			mapper.map(updateUse, dto);
			structure.setMessage("User deleted........");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<UserDto>>(structure, HttpStatus.OK);
		}
		
		throw new UserNotFound("User not found for given id "+userId);
	}
//	

	
	
}
