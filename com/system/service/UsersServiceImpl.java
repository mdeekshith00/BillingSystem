package com.system.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.exception.ResourceNotFound;
import com.system.model.Billing;
import com.system.model.Products;
import com.system.model.Users;
import com.system.repositary.BillingRepositary;
import com.system.repositary.UsersRepositary;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepositary userRepositary;
	


	

	@Override
	public Users addUser(Users user) {
		// TODO Auto-generated method stub
		return userRepositary.save(user);
	}

	@Override
	public Users getUserById(int uId) {
		// TODO Auto-generated method stub
		return userRepositary.findById(uId).orElseThrow(() -> 
		new ResourceNotFound("Users", "Id", uId));
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepositary.findAll();
	}

//	@Override
//	public String deleteUserById(int id) {
//		// TODO Auto-generated method stub
//		userRepositary.deleteById(id);
//		return "deleted Users  id : " + id;
//	}




		
}


