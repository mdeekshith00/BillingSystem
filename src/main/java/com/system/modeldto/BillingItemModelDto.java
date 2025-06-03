package com.system.modeldto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.system.model.Billing;
import com.system.model.UserProducts;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class BillingItemModelDto {
	private Integer itemId;
	 
	private int quantity;
	
	private BigDecimal price; // Unit price (copied from Products)
	
	private BigDecimal totalAmount; // price * quantity
	
	private LocalDateTime createdAt;
	
	private Billing billing;
	  
   private List<UserProducts> UserProductId;

}
