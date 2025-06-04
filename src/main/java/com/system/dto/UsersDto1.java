package com.system.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.system.model.Billing;
import com.system.model.BillingItem;
import com.system.model.UserProducts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Component
public class UsersDto1 {
	
	private Integer uId;
	
	private String uName;
	
	private String mobileNo;
	
	private String eMail;
	
    private List<UserProducts> userProducts;
	
	
	private List<Billing> bId;



}
