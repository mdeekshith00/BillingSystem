package com.system.modeldto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class ProductsModelDto {
	
   private Integer  productId;
	
	private String productName;
	
	private String productCompany;
	
	private String description;
	
	private BigDecimal MRP;
	
	private Integer quantityAvailable;
	
	private LocalDate expiryDate;
	
	private String unit; 
	
	private LocalDateTime createdAt; //  when the product was added.
	
	private LocalDateTime updatedAt;

}
