package com.admindroid.spring.springboot.bookmyshow.boot.dto;

import com.admindroid.spring.springboot.bookmyshow.boot.entity.Theatre;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TheatreAdminDto 
{
	private int theatreAdminId;
	private String theatreAdminName;
	private long theatreAdminContact;
	private String theatreAdminEmail;
	private Theatre theatre;
	
}
