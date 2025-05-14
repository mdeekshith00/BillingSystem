package com.system.service;

import java.util.List;

import com.system.model.Billing;

public interface BillingService {
	
	Billing addBilling(Billing billing);
	Billing addBilling(Billing billing, long uId);
	Billing getBillById(long bId);
	List<Billing> getAllBill();
	
	String addBillsForUser(long uId);


}
