package com.admindroid.spring.springboot.bookmyshow.boot.dto;

import java.util.List;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Theatre;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto 
{
	private int adminId;
	private String adminName;
	private long adminContact;
	private String adminMail;
	private List<Theatre> theatresList;
}
