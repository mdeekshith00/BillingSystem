package com.system.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
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
     @JoinColumn(name = "billing_id")
	private Billing billing;
	  
	 @ManyToOne
     @JoinColumn(name = "product_id")
	private Products products;

}

//
//    @PrePersist
//    @PreUpdate
//    public void calculateTotal() {
//        if (product != null && price == null) {
//            this.price = product.getPrice();
//        }
//        if (price != null) {
//            this.totalAmount = price.multiply(BigDecimal.valueOf(quantity));
//        }
//        this.createdAt = LocalDateTime.now();
//    }
//}
