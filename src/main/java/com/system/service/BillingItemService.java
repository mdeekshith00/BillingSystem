package com.system.service;

import java.util.List;

import com.system.model.BillingItem;

public interface BillingItemService {
	
	BillingItem addBillingItem(BillingItem billingItem);
	BillingItem getBiilItemById(Integer itemId);
	List<BillingItem> getAllBillingItems();
	
	String generateFullBillForUser(Integer uId);
//   String generateBil(Integer uId);

}
