package com.system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.dto.BillingItemDto;
import com.system.serviceImpl.BillingItemServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/billing-item")
public class BillingItemController {
	
	private final BillingItemServiceImpl billingService;
	
//	@PostMapping("/add")
//	public ResponseEntity<BillingItemDto> addBillingItem(@RequestBody BillingItemModelDto billingItemModelDto) {
//		return new ResponseEntity<>(billingService.addBillingItem(billingItemModelDto) , HttpStatus.CREATED);
//	}
	
	@GetMapping("/{itemId}")
	public ResponseEntity<BillingItemDto> getBiilItemById(@PathVariable Integer itemId) {
		return new ResponseEntity<>(billingService.getBilItemById(itemId) , HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<BillingItemDto>> getAllBillingItems() {
		return new ResponseEntity<>(billingService.getAllBillingItems() , HttpStatus.OK);
	}

	@PostMapping("/billforuser")
	public ResponseEntity<?> generateFullBillForUser(@RequestParam Integer uId) {
		return new ResponseEntity<>(billingService.generateFullBillForUser(uId) , HttpStatus.OK);
	}
}

