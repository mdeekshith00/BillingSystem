package com.system.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "itemId")

@Entity
public class BillingItem {
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE)
	 @JsonProperty("itemId")
	private Integer itemId;
	 
	private int quantity;
	
	private BigDecimal price; // Unit price (copied from Products)
	
	private BigDecimal totalAmount; // price * quantity
	
	private LocalDateTime createdAt;
	
	 @ManyToOne
     @JoinColumn(name = "bId")
	private Billing billing;
	  

	 
	 @OneToMany(mappedBy = "billingItem" , cascade = CascadeType.ALL)
		private List<UserProducts> UserProductId;

}

