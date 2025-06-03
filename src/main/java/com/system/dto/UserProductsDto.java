package com.system.dto;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class UserProductsDto {
	
	 private Integer UserProductId;
	    
	 private BigDecimal mrp; 
	 
	 private int quantity;

}
