package com.admindroid.spring.springboot.bookmyshow.boot.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Admin;
import com.admindroid.spring.springboot.bookmyshow.boot.repo.AdminRepo;


@Repository
public class AdminDao
{
	@Autowired
	AdminRepo adminRepo;

	public Admin saveAdmin(Admin admin)
	{
		return adminRepo.save(admin);
	}
	public Admin  findAdmin(int adminId)
	{
		Optional<Admin> opAdmin=adminRepo.findById(adminId);
		if(opAdmin.isPresent())
		{
			return opAdmin.get();
		}
		return null;
	}
	public Admin findByEmail(String adminEmail) {
		return adminRepo.findByMail(adminEmail);
	}
	
	public Admin deleteAdmin(int adminId)
	{
		Admin admin=findAdmin(adminId);
		adminRepo.delete(admin);
		
		return admin;
	}
	
	public Admin updateAdmin(Admin admin, int adminId)
	{
		Admin exAdmin=findAdmin(adminId);
		if(exAdmin != null)
		{
			admin.setAdminId(adminId);
			return adminRepo.save(admin);
		}
		return null;
	}
//	public List<Admin> findAllAdmins()
//	{
//		return adminRepo.findAll();
//	}
	
//	public Admin adminLogin(String adminMail)
//	{
//		return adminRepo.adminLogin(adminMail);
//	}

}
