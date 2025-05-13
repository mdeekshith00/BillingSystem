package com.system.service;

import java.util.List;

import com.system.model.Billing;

public interface BillingService {
	
	Billing addBilling(Billing billing);
	Billing getBillById(int id );
	List<Billing> getAllBill();

	void addBillsForAllUsers();
	String  addBillsForUsers(int id);
	
//	void addBill();

}
