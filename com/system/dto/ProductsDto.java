package com.system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

import com.system.model.Users;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class ProductsDto {
	
	private String pName;

	private String pCompany;

	private BigDecimal MRP;

	private LocalDate expiryDate;
	
	@ManyToOne
	@JoinColumn(name = "u_id" )
	private Users user;

}
