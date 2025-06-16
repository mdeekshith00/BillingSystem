package com.system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.system.model.UserProducts;
import com.system.model.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class ProductsDto {
	
    private Integer  productId;
	
	private String productName;
	
	private String productCompany;
	
	private String description;
	
	private BigDecimal MRP;
	
	private Integer quantityAvailable;
	
	private LocalDate expiryDate;
	
	private String unit; 
	
	private String ingredients;
	
	private LocalDateTime createdAt; //  when the product was added.
	
	private LocalDateTime updatedAt; // when the product was updated at store 

}