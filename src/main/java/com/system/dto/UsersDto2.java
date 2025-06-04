package com.system.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class UsersDto2 {
	
private Integer uId;
	
	private String uName;
	
	private String mobileNo;
	
	private String eMail;

}
