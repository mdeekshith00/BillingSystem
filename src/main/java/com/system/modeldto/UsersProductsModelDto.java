package com.system.modeldto;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import com.system.model.BillingItem;
import com.system.model.Products;
import com.system.model.Users;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Component
public class UsersProductsModelDto {
	
	 private Integer UserProductId;
	    
	 private BigDecimal mrp; // copied from products

	 private Users user;

	 private Products product;

     private BillingItem billingItem;

	 private int quantity;

}
