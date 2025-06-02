package com.system.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
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
	
	@Column
	private String productName;
	@Column
	private String productCompany;
	@Column
	private String description;
	@Column
	private BigDecimal MRP;
	@Column
	private Integer quantityAvailable;
	@Column
	private LocalDate expiryDate;
	@Column
	private String unit; 
	
	@Column
	private LocalDateTime createdAt; //  when the product was added.
	@Column
	private LocalDateTime updatedAt; // when the product was updated at store 
	

	
	@OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
	private List<UserProducts> userProducts;
	


}





