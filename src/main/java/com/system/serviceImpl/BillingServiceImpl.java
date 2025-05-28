package com.system.serviceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.BillingDto;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Billing;
import com.system.model.Products;
import com.system.model.Users;
import com.system.repositary.BillingRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.BillingService;

@Service
public class BillingServiceImpl  implements BillingService {
	
	@Autowired
	private BillingRepositary billingRepositary;
	
	@Autowired
	private UsersRepositary  uBillService;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public Billing addBilling(Billing billing) {
		// TODO Auto-generated method stub
		Billing b =  billingRepositary.save(billing);
//		BillingDto bDto = modelMapper.map(b, BillingDto.class);
		return b;
		
	}
		
	@Override
	public Billing getBillById(Integer id) {
		// TODO Auto-generated method stub
		Billing b =  billingRepositary.findById(id).orElseThrow(() ->
		new ReourceNotFoundException("Billing Id Not Found On this" + id));
		
//		BillingDto bDto = modelMapper.map(b, BillingDto.class);

		return b;
	
	}

	@Override
	public Billing addBilling(Billing billing , Integer uId) {
		// TODO Auto-generated method stub
		
		Users userb = uBillService.findById(uId).orElseThrow(() -> 
		new ReourceNotFoundException("Billing Id Not Found On this UserID : " + uId));
		
		
		billing.setUser(userb);
		Billing bill = billingRepositary.save(billing);
		return bill;
	}

	@Override
	public List<Billing> getAllBill() {
		// TODO Auto-generated method stub
		List<Billing> billList =  billingRepositary.findAll();
//		List<BillingDto> billDtoList = billList.stream().map(a ->modelMapper.map(billList, BillingDto.class)).toList();
		return billList;
	}


//	@Override
//	public String addBillsForUser(Integer uId) {
//		// TODO Auto-generated method stub
//		    Users user = uBillService.findById(uId)
//				        .orElseThrow(() -> new ReourceNotFoundException("Billing Id Not Found On this UserId : " + uId));
//				
//				    List<Products> userProducts = user.getProductId(); 
//				
//				    BigDecimal totalAmount = userProducts.stream()
//				        .map(Products::getMRP)
//				        .filter(Objects::nonNull)
////				        .filter(mrp -> mrp != null)
//				        .reduce(BigDecimal.ZERO, BigDecimal::add);
//				
//				    Billing billing = billingRepositary.findByUser(user);
//
//				
//				    billing.setUser(user);
//				    billing.setBAmount(totalAmount);
//				
//				    billingRepositary.save(billing);
//				
//				    return "Billing generated for user " + user.getUName() + ": Rs. " + totalAmount;
//				}

	
			

}


