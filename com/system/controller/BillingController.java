package com.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.model.Billing;
import com.system.service.BillingServiceImpl;

@RestController
@RequestMapping("/api/billing")
public class BillingController {
	
	@Autowired
	private BillingServiceImpl bill1Service1;
	
	@PostMapping("/add") 
	public ResponseEntity<Billing> createBill(@RequestBody Billing billing) {
		return new ResponseEntity<>(bill1Service1.addBilling(billing) ,HttpStatus.OK);	
	}
	
	@PostMapping("/add/{uId}") 
	public ResponseEntity<Billing> addBilling(@RequestBody Billing billing , @PathVariable long uId) {
		return new ResponseEntity<>(bill1Service1.addBilling(billing, uId) ,HttpStatus.OK);	
	}
	@GetMapping("/{id}")
	public ResponseEntity<Billing> getBillById(@PathVariable int id) {
		return new ResponseEntity<>(bill1Service1.getBillById(id) ,HttpStatus.OK);
		
	}
	@GetMapping("/")
	public ResponseEntity<List<Billing>> getAllBill(){
		return new ResponseEntity<>(bill1Service1.getAllBill() ,HttpStatus.OK);
	}
	
//	public void someMethod(@RequestParam(value = "uId", required = false) Long uId) {
//	    // method body
//	}
	@GetMapping("/billingUser")
	public ResponseEntity<?> generateBill(@RequestParam(value = "uId", required = false) Long uId) {
		return new ResponseEntity<>(bill1Service1.addBillsForUser(uId) , HttpStatus.OK);
	
	}


}

