package com.system.service;

import java.util.List;
import com.system.dto.BillingDto;
import com.system.model.Billing;

public interface BillingService {
	
	BillingDto addBilling(Billing billing);
	Billing addBilling(Billing billing, long uId);
	BillingDto getBillById(long bId);
	List<BillingDto> getAllBill();
	
	String addBillsForUser(long uId);


}
