package com.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.model.BillingItem;
import com.system.serviceImpl.BillingItemServiceImpl;

@RestController
@RequestMapping("/billing-item")
public class BillingItemController {
	@Autowired
	private BillingItemServiceImpl billingService;
	
	@PostMapping("/add")
	public ResponseEntity<BillingItem> addBillingItem(@RequestBody BillingItem billingItem) {
		return new ResponseEntity<>(billingService.addBillingItem(billingItem) , HttpStatus.CREATED);
	}
	

}
