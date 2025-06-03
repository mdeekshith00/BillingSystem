package com.system.modeldto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class UsersModelDto {
	
	private Integer uId;
	
	private String uName;
	
	private String mobileNo;
	
	private String eMail;

}
