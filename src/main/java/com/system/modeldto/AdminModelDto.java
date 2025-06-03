package com.system.modeldto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class AdminModelDto {
	
    private Integer aId;
	
	private String userName;

	private String password;

	private String role;
	

}
