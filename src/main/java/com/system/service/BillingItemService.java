package com.system.service;

import java.util.List;

import com.system.dto.BillingItemDto;
import com.system.modeldto.BillingItemModelDto;

public interface BillingItemService {
	
	BillingItemDto addBillingItem(BillingItemModelDto billingItemModelDto);
	BillingItemDto getBiilItemById(Integer itemId);
	List<BillingItemDto> getAllBillingItems();
	
	String generateFullBillForUser(Integer uId);
//   String generateBil(Integer uId);

}
