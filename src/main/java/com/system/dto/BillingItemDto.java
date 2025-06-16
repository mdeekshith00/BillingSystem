package com.system.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.system.model.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingItemDto {
	
	private Integer itemId;
	 
	private int quantity;
	
	private BigDecimal price; 
	
	private String productName;
	
	private String productComapny;
	
	private UsersDto2 user;
	
	


}
