package com.admindroid.spring.springboot.bookmyshow.boot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Theatre;

public interface TheatreRepo extends JpaRepository<Theatre, Integer>
{

}
