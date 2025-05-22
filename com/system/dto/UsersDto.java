package com.system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Component
public class UsersDto {
	
	private String uName;
	
	private String mobileNo;
	
	private String eMail;
	// products 
	private long  pId;

	private String pName;

	private String pCompany;

	private BigDecimal MRP;
	
	private LocalDate expiryDate;
	
	// bill details
	private long bId;
	
	private BigDecimal bAmount;
	

	
}
