package com.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {

	    private Integer productId;
	    
	    private String productName;
	    
	    private String productCompany;
	    
	    private String description;
	    
	    private int quantityAvailable;
	    
	    private String expiryDate;
	    
	    private String unit;
	


}
