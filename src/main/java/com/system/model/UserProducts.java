package com.system.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "UserProductId")

@Entity
public class UserProducts {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @JsonProperty("UserProductId")
	    private Integer UserProductId;
	    
	    private BigDecimal mrp; // copied from products

	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private Users user;

	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Products product;

	    @ManyToOne
	    @JoinColumn(name = "itemId")
	    private BillingItem billingItem;

	    private int quantity;
    
   
}
