package com.system.service;

import java.util.List;
import com.system.dto.BillingDto;
import com.system.model.Billing;

public interface BillingService {
	
	Billing addBilling(Billing billing);
	Billing addBilling(Billing billing, Integer uId);
	Billing getBillById(Integer bId);
	List<Billing> getAllBill();
	
//	String addBillsForUser(Integer uId);


}
