package com.system.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.system.model.Users;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component
public class BillingDto {
	
	private BigDecimal bAmount;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private Users user;
	

}
