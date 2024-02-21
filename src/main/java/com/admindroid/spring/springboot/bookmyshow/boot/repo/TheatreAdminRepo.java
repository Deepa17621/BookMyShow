package com.admindroid.spring.springboot.bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.TheatreAdmin;

public interface TheatreAdminRepo extends JpaRepository<TheatreAdmin, Integer>
{
	@Query("select t from TheatreAdmin t where t.theatreAdminEmail=?1")
	public TheatreAdmin findByEmail(String theatreAdminEmail);
}
