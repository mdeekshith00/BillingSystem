package com.system.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.model.BillingItem;
import com.system.repositary.BillingItemRepositary;
import com.system.service.BillingItemService;


@Service
public class BillingItemServiceImpl  implements BillingItemService {
	
	@Autowired
	private BillingItemRepositary billingItemRepositary;
	

	@Override
	public BillingItem addBillingItem(BillingItem billingItem) {
		// TODO Auto-generated method stub
		return null;
	}

}
