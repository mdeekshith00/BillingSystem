package com.system.service;

import java.util.List;

import com.system.dto.AdminDto;
import com.system.dto.BillingItemDto;
import com.system.dto.UsersDto1;
import com.system.modeldto.AdminModelDto;

public interface AdminService {
	
    String verify(AdminModelDto adminModelDto);
//	AdminDto signUp(AdminModelDto adminModelDto);
	AdminDto register(AdminModelDto adminModelDto);
	
	 AdminDto getById(Integer aId);
	 UsersDto1 setProductsToUsers(Integer uId, Integer productId, int quantity);
	List<BillingItemDto> getAllBillsByProduct(String productName , String productCompany);

	
}
