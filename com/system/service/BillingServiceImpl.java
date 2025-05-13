package com.system.service;

import java.math.BigDecimal;
import java.util.List;

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

	@Autowired 
	private ProductsServiceImpl pBillService;
	
	
	@Override
	public Billing getBillById(int id) {
		// TODO Auto-generated method stub
		return billingRepositary.findById(id).orElseThrow(() -> new ResourceNotFound("Bill", "Id", id));
	}

	@Override
	public Billing addBilling(Billing billing) {
		// TODO Auto-generated method stub
		return billingRepositary.save(billing);
	}

	@Override
	public List<Billing> getAllBill() {
		// TODO Auto-generated method stub
		return billingRepositary.findAll();
	}

	@Override
	public void addBillsForAllUsers() throws HibernateException {
		// TODO Auto-generated method stub
		List<Users> allUsers = uBillService.findAll();
				    for (Users user : allUsers) {
				        int userId = user.getUId();
		
				        List<Products> userProducts = pBillService.findByUserId(userId); // findByUserId(userId)
		
				        BigDecimal totalAmount = userProducts.stream()
				                .map(Products::getMRP) // make sure this returns BigDecimal
				                .filter(mrp -> mrp != null)
				                .reduce(BigDecimal.ZERO, BigDecimal::add);
		
				        
				        if (totalAmount.compareTo(BigDecimal.ZERO) > 0) {
				          Billing billing = billingRepositary.findByUser(user); 
				      
				          if (billing == null) {
				              billing = new Billing();
				              billing.setUser(user);
				          }
				      
				          billing.setBAmount(totalAmount);
				          billingRepositary.save(billing); 
				      }
				       
				    }
		 }
	@Override
	public String addBillsForUsers(int id) {
		// TODO Auto-generated method stub
		
//		Users user1 = uBillService.findById(id).orElseThrow(() -> new ResourceNotFound("Users", "Id", id));
//		
//		int userId1 = user1.getUId();
//		
//		List<Products> userProduc = pBillService.getAllProducts();
////		pBillService.findByUserId(userId);
//		if(userId == ) {
//			
//		}
			
			
			
		return "Updated Bill On " + id;
	}
}



