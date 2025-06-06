package com.system.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsersProductsDto1 {
	
	   private Integer userProductId;
	   
	   private BigDecimal mrp;
	   
	   private int quantity;
	   
//	   private ProductDto product;

}
