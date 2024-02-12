package com.admindroid.spring.springboot.bookmyshow.boot.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.admindroid.spring.springboot.bookmyshow.boot.dao.AdminDao;
import com.admindroid.spring.springboot.bookmyshow.boot.dto.AdminDto;
import com.admindroid.spring.springboot.bookmyshow.boot.entity.Admin;
import com.admindroid.spring.springboot.bookmyshow.boot.exception.AdminNotFound;
import com.admindroid.spring.springboot.bookmyshow.boot.util.ResponseStructure;

@Service
public class AdminService
{
	@Autowired
	AdminDao adminDao;
	
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin) 
	{
		AdminDto dto=new AdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(adminDao.saveAdmin(admin), dto);
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		structure.setMessage("Admin saved success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.CREATED);
	}
	
	//Find Admin Detail 
	public ResponseEntity<ResponseStructure<AdminDto>>  findAdmin(int adminId)
	{
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		Admin admin=adminDao.findAdmin(adminId);
		AdminDto dto=new AdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(admin, dto);
		if(admin != null)
		{
			structure.setMessage("Admin found........");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.FOUND);
		}
		throw new AdminNotFound("Admin not found for given id "+adminId);
		
		
	}
	//Delete Admin Details
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(int adminId)
	{
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		
		Admin admin=adminDao.findAdmin(adminId);
		
		if(admin != null)
		{
		Admin delAdmin=adminDao.deleteAdmin(adminId);
		AdminDto dto=new AdminDto();
		ModelMapper mapper=new ModelMapper();
		mapper.map(delAdmin, dto);
		structure.setMessage("Admin deleted........");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
		}
		throw new AdminNotFound("Admin not found for given id "+adminId);
		
	}
	//Update Student details
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(Admin admin, int adminId)
	{
		ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
		
		Admin adm=adminDao.findAdmin(adminId);
		if(adm != null)
		{
			Admin updateAdm=adminDao.updateAdmin(admin, adminId);
			AdminDto dto=new AdminDto();
			ModelMapper mapper=new ModelMapper();
			mapper.map(updateAdm, dto);
			structure.setMessage("Admin deleted........");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure, HttpStatus.OK);
		}
		
		throw new AdminNotFound("Admin not found for given id "+adminId);
	}

}
