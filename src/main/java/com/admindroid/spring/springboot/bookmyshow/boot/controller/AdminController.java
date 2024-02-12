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

import com.admindroid.spring.springboot.bookmyshow.boot.dto.AdminDto;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Admin;
import com.admindroid.spring.springboot.bookmyshow.boot.service.AdminService;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;


@RestController
@RequestMapping("/admin")
public class AdminController
{
	@Autowired
	AdminService adminService;
	
	@Autowired
	AdminDto adminDto;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@RequestBody Admin admin)
	{
		return adminService.saveAdmin(admin);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<AdminDto>>  findStudent(@RequestParam int adminId)
	{
		return adminService.findAdmin(adminId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(@RequestParam int adminId)
	{
		return adminService.deleteAdmin(adminId);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(@RequestBody Admin admin,@RequestParam int adminId)
	{
		return adminService.updateAdmin(admin, adminId);
	}
}
