package com.system.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor


public class UsersDto1 {
	
	private Integer uId;
	
    private String uName;
    
    private String mobileNo;
    
    private String eMail;
    
    private List<UsersProductsDto1> userProducts;
    
    private List<BillingDto1> bId;


}
