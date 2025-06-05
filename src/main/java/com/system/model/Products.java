package com.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productId")
//@JsonAutoDetect(getterVisibility=Visibility.NONE)
@Entity
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("productId")
	private Integer  productId;
	
	@Column(nullable = false , unique = true)
	@Size(min=2, max=30)
	private String productName;
	@Column(nullable = false , unique = true)
	private String productCompany;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private BigDecimal MRP;
	@Column
	@NotNull(message = "quantityAvailable should be greater than 1 atleast in store")
	private Integer quantityAvailable;
	@Column(nullable = false)
	@Future(message = " product expiryDate Should be future")
	private LocalDate expiryDate;
	@Column(nullable = false )
	private String unit; 
	
	@Column(nullable = false)
	@PastOrPresent(message = " createdAt should be in past or present")
	private LocalDateTime createdAt; //  when the product was added.
	@Column(nullable = false)
	private LocalDateTime updatedAt; // when the product was updated at store 
	

	@JsonBackReference
	@OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
	private List<UserProducts> userProducts;
	


}





