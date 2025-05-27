package com.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "pId")
@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("productId")
	private Integer  productId;
	
	@Column
	private String productName;
	private String productCompany;
	private String description;
	private BigDecimal MRP;
	private Integer quantityAvailable;
	private LocalDate expiryDate;
	private String unit; 
	private LocalDateTime createdAt; //  when the product was added.
	private LocalDateTime updatedAt; // when the product was updated at store 
	
	@ManyToOne
	@JoinColumn(name = "u_id" )
	private Users user;
	
	@OneToMany(mappedBy = "products" , cascade = CascadeType.ALL)
	private List<BillingItem> BillingItem;

}





