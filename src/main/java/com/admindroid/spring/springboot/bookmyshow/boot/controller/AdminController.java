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

import com.admindroid.spring.springboot.bookmyshow.boot.dto.AdminDto;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Admin;
import com.admindroid.spring.springboot.bookmyshow.boot.service.AdminService;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

import jakarta.validation.Valid;


@RestController
@RequestMapping("admin")
public class AdminController
{
	@Autowired
	AdminService adminService;
	
//	@Autowired
//	AdminDto adminDto;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@Valid @RequestBody Admin admin)
	{
		return adminService.saveAdmin(admin);
	}
	
	@PutMapping("assignTheatresToAdmin")
	ResponseEntity<ResponseStructure<AdminDto>> assignTheatresToAdmin(@Valid@RequestParam String adminEmail,@RequestParam String adminPassword, @RequestParam int adminId,@RequestBody List<Integer> theatreIds){
		return adminService.assignTheatresToAdmin(adminEmail, adminPassword,adminId, theatreIds);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<AdminDto>>  findAdmin(@Valid @RequestParam int adminId)
	{
		return adminService.findAdmin(adminId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(@Valid @RequestParam int adminId, String adminMail, String adminPassword)
	{
		return adminService.deleteAdmin(adminId, adminMail, adminPassword);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(@Valid @RequestBody Admin admin,@RequestParam int adminId, String adminMail, String adminPassword)
	{
		return adminService.updateAdmin(admin, adminId, adminMail, adminPassword);
	}
}
