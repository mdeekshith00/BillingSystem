package com.system.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.exception.ReourceNotFoundException;
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
		return billingItemRepositary.save(billingItem);
	}


	@Override
	public BillingItem getBiilItemById(Integer itemId) {
		// TODO Auto-generated method stub
		return billingItemRepositary.findById(itemId).orElseThrow(() ->
		new ReourceNotFoundException("BillingItems Id Not Found On this ID:" +itemId ));
	}


	@Override
	public List<BillingItem> getAllBillingItems() {
		// TODO Auto-generated method stub
		return billingItemRepositary.findAll();
	}
	

}
