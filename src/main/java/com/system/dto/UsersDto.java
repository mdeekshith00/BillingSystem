package com.system.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.system.model.BillingItem;
import com.system.model.Products;
import com.system.model.UserProducts;
import com.system.model.Users;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class UsersDto {
	
	
	// user details
	private Integer uId;
	private String uName;
	private String mobileNo;
	private String eMail;
	
	// userProducts 
	 private List<UserProducts> productList; 
	    private BigDecimal mrp; 
//	    private Users user;
//	    private Products product;
//	    private BillingItem billingItem;
	    private int uquantity;

	// products 
	private long  pId;
//	private String pName;
//	private String pCompany;
	private BigDecimal MRP;
	private LocalDate expiryDate;
	
	// bill details
	private long bId;
	
	//billingItems
//	private Integer itemId;	 
	private int quantity;
	private BigDecimal price; 
	private BigDecimal totalAmount; 
	private LocalDateTime createdAt;
	

	
}
