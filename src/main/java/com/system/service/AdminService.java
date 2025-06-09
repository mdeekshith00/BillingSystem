package com.system.service;

import java.util.List;

import com.system.dto.AdminDto;
import com.system.dto.BillingDto;
import com.system.model.Billing;
import com.system.modeldto.AdminModelDto;

public interface AdminService {
	
    String verify(AdminModelDto adminModelDto);
	AdminDto signUp(AdminModelDto adminModelDto);
	AdminDto register(AdminModelDto adminModelDto);
	
	AdminDto getById(Integer aId);
	
	List<BillingDto> getAllBillsByProduct(String productName , String productCompany);

	
	
	

}
