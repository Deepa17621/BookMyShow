package com.admindroid.spring.springboot.bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.TheatreAdmin;

public interface TheatreAdminRepo extends JpaRepository<TheatreAdmin, Integer>
{
	
}
