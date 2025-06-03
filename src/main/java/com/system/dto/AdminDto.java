package com.system.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Component
public class AdminDto {
	
	private Integer aId;
	
	private String userName;

	private String role;
	
	

}
