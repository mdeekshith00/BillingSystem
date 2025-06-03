package com.system.service;

import java.util.List;

import com.system.dto.BillingDto;
import com.system.model.Billing;
import com.system.modeldto.BillingModelDto;

public interface BillingService {
	
	BillingDto addBilling(BillingModelDto billingModelDto);
//	BillingDto addBilling(Billing billing, Integer uId);
	BillingDto getBillById(Integer bId);
	List<BillingDto> getAllBill();
	
//	String addBillsForUser(Integer uId);


}
