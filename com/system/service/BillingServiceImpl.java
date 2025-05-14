package com.system.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.exception.ResourceNotFound;
import com.system.model.Billing;
import com.system.model.Products;
import com.system.model.Users;
import com.system.repositary.BillingRepositary;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UsersRepositary;

@Service
public class BillingServiceImpl  implements BillingService {
	
	@Autowired
	private BillingRepositary billingRepositary;
	
	@Autowired
	private UsersRepositary  uBillService;
	
	
	@Override
	public Billing addBilling(Billing billing) {
		// TODO Auto-generated method stub
		return billingRepositary.save(billing);
	}
		
	@Override
	public Billing getBillById(long id) {
		// TODO Auto-generated method stub
		return billingRepositary.findById(id).orElseThrow(() -> new ResourceNotFound("Bill", "Id", id));
	}

	@Override
	public Billing addBilling(Billing billing , long uId) {
		// TODO Auto-generated method stub
		
		Users userb = uBillService.findById(uId).orElseThrow(() -> 
		new ResourceNotFound("Users", "uId", uId));
		
		
		billing.setUser(userb);
		Billing bill = billingRepositary.save(billing);
		return bill;
	}

	@Override
	public List<Billing> getAllBill() {
		// TODO Auto-generated method stub
		return billingRepositary.findAll();
	}


	@Override
	public String addBillsForUser(long uId) {
		// TODO Auto-generated method stub
		    Users user = uBillService.findById(uId)
				        .orElseThrow(() -> new ResourceNotFound("Users", "Id", uId));
				
				    List<Products> userProducts = user.getPId(); 
				
				    BigDecimal totalAmount = userProducts.stream()
				        .map(Products::getMRP)
				        .filter(Objects::nonNull)
//				        .filter(mrp -> mrp != null)
				        .reduce(BigDecimal.ZERO, BigDecimal::add);
				
				    Billing billing = billingRepositary.findByUser(user);

				
				    billing.setUser(user);
				    billing.setBAmount(totalAmount);
				
				    billingRepositary.save(billing);
				
				    return "Billing generated for user " + user.getUName() + ": Rs. " + totalAmount;
				}

	
			

}


