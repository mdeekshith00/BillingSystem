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
import org.springframework.web.bind.annotation.RestController;

import com.system.dto.BillingDto;
import com.system.modeldto.BillingModelDto;
import com.system.serviceImpl.BillingServiceImpl;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@RestController
@RequestMapping("/billing")
public class BillingController {
	
	
	private final  BillingServiceImpl bill1Service1;
	
	@PostMapping("/add") 
	public ResponseEntity<BillingDto> createBill(@RequestBody BillingModelDto billingModelDtoling) {
		return new ResponseEntity<>(bill1Service1.addBilling(billingModelDtoling) ,HttpStatus.OK);	
	}

	@GetMapping("/{id}")
	public ResponseEntity<BillingDto> getBillById(@PathVariable Integer id) {
		return new ResponseEntity<>(bill1Service1.getBillById(id) ,HttpStatus.OK);
		
	}
	@GetMapping
	public ResponseEntity<List<BillingDto>> getAllBill(){
		return new ResponseEntity<>(bill1Service1.getAllBill() ,HttpStatus.OK);
	}

}

