package com.admindroid.spring.springboot.bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>
{
	@Query("select u from User u where u.userMail=?1")
	public User findByMail(String userMail);
}
