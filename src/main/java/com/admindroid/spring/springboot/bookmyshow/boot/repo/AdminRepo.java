package com.admindroid.spring.springboot.bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Admin;

public interface AdminRepo  extends JpaRepository<Admin, Integer>
{
	@Query("select a from Admin a where a.adminMail=?1 ")
	public Admin findByMail(String adminMail); 
}
