package com.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class AdminDto {
	
	private Integer aId;
	
	private String userName;

	private String role;
	
	private String resetToken;
	
	

}
