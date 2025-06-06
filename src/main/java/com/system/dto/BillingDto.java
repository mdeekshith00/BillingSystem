package com.system.dto;

import java.util.List;

import com.system.model.BillingItem;
import com.system.model.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class BillingDto {

	private Integer bId;
	
	private UsersDto2 user;

//	private List<BillingItemDto> BillItem;



	

}
