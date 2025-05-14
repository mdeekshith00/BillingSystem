package com.system.service;

import java.util.List;

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
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepositary userRepositary;
	
	@Autowired
	private ProductsRepositary pUserRepositary;
	


	@Override
	public Users addUser(Users user) {
		// TODO Auto-generated method stub
		return userRepositary.save(user);
	}

	@Override
	public Users getUserById(long uId) {
		// TODO Auto-generated method stub
		return userRepositary.findById(uId).orElseThrow(() -> 
		new ResourceNotFound("Users", "Id", uId));
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepositary.findAll();
	}

	
	@Override
	public Users setProductsToUsers(long uId, long pId) {
		// TODO Auto-generated method stub
		Users user = userRepositary.findById(uId).orElseThrow(() -> 
		new ResourceNotFound("Users", "Id", uId));
		
		Products products = pUserRepositary.findById(uId).orElseThrow(() -> 
		new ResourceNotFound("Users", "Id", uId));
		
		products.setUser(user);
		user.getPId().add(products);
		pUserRepositary.save(products);
		
		return user;
	}

		
}



