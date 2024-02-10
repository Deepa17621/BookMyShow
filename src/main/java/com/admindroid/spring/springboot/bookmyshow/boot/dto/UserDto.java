package com.admindroid.spring.springboot.bookmyshow.boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto 
{
	private int userId;
	private String userName;
	private long userContact;
	private String userMail;
}
