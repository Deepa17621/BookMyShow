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

import com.admindroid.spring.springboot.bookmyshow.boot.dto.UserDto;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.User;
import com.admindroid.spring.springboot.bookmyshow.boot.service.UserService;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@RestController
@RequestMapping("/user")
public class UserController  
{
	@Autowired
	UserService userService;
	
	@Autowired
	UserDto userDto;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<UserDto>> saveUser(@RequestBody User user)
	{
		return userService.saveUser(user);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<UserDto>>  findUser(@RequestParam int userId)
	{
		return userService.findUser(userId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<UserDto>> deleteUser(@RequestParam int userId)
	{
		return userService.deleteUser(userId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<UserDto>> updateUser(@RequestBody User user,@RequestParam int userId)
	{
		return userService.updateUser(user, userId);
	}
	
	@GetMapping("userLogin")
	ResponseEntity<ResponseStructure<UserDto>> findUser(@RequestParam String userEmail,@RequestParam String userPassword){
		return userService.findByEmail(userEmail, userPassword);
	}
}
