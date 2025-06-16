package com.system.dto;

import java.util.List;
import com.system.model.BillingItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsersDtoMax {
    private Integer uId;
	
	private String uName;
	
	private String mobileNo;
	
	private String eMail;
	
	private AdminDto admin;

    private List<UserProductsDto> userProducts; 
    
    private List<BillingItemDtoMin> billingItems;

}
