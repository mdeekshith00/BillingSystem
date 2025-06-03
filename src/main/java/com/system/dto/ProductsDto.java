package com.system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.system.model.UserProducts;
import com.system.model.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class ProductsDto {
	
    private Integer  productId;
	
	private String productName;
	
	private String productCompany;
	
	private String description;
	
	private BigDecimal MRP;
	
	private Integer quantityAvailable;
	
	private LocalDate expiryDate;
	
	private String unit; 
	
	
	private LocalDateTime createdAt; //  when the product was added.
	
	private LocalDateTime updatedAt; // when the product was updated at store 
	
	private List<UserProducts> userProducts;

}
