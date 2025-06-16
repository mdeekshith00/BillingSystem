package com.system.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BillingItemDtoMin {
	
	private Integer itemId;
	 
	private int quantity;
	
	private BigDecimal price; // Unit price (copied from Products)
	
	private BigDecimal totalAmount; // price * quantity

}
