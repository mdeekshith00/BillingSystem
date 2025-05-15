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
	
	private String name;
	
	private String role;
	
	

}
