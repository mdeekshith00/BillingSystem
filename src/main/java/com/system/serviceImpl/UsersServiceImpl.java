package com.system.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.UsersDto;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Billing;
import com.system.model.Products;
import com.system.model.Users;
import com.system.repositary.BillingRepositary;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersRepositary userRepositary;
	
	@Autowired
	private ProductsRepositary pUserRepositary;
	@Autowired
	private BillingRepositary bUserRepositary;
	@Autowired 
	private UsersDto userDto;
	
	@Autowired
	private ModelMapper modelMapper;
	


	@Override
	public Users addUser(Users user) {
		// TODO Auto-generated method stub
		Users u = userRepositary.save(user);
//		UsersDto u1 = modelMapper.map(u,UsersDto.class);
		return u;
	}
 
//	 get user and billing  details using thier ids
	@Override
	public Users getUserById(Integer uId , Integer bId) {
		// TODO Auto-generated method stub
		Users u =  userRepositary.findById(uId).orElseThrow(() -> 
		new ReourceNotFoundException("User Id Not Found On this UserID : " + uId));
		
		Billing b = bUserRepositary.findById(bId).orElseThrow(() -> 
		new ReourceNotFoundException("Billing Id Not Found On this UserID : " + bId));
		
		List<Products> p = pUserRepositary.findAll();
		
		userDto.setUName(u.getUName());
		userDto.setMobileNo(u.getMobileNo());
		userDto.setEMail(u.getEMail());
		

		// billing 
		userDto.setBId(b.getBId());		
//		userDto.setBAmount(b.getBAmount());
		return u;
		
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		List<Users> list =  userRepositary.findAll();
//		List<UsersDto> list1 = list.stream().map(a -> modelMapper.map(a , UsersDto.class)).toList();
		return list;
	}

	@Override
	public Users getUserById(Integer uId) {
		// TODO Auto-generated method stub
		return userRepositary.findById(uId).orElseThrow(() -> 
		new ReourceNotFoundException("User Id Not Found On this UserID : " + uId));
	}

	
//	@Override
//	public UsersDto setProductsToUsers(Integer uId, Integer productId) {
//		// TODO Auto-generated method stub
//		Users user = userRepositary.findById(uId).orElseThrow(() -> 
//		new ReourceNotFoundException("User Id Not Found On this UserID : " + uId));
//		modelMapper.map(user, UsersDto.class);	
//	
//		Products products = pUserRepositary.findById(productId).orElseThrow(() -> 
//		new ReourceNotFoundException("Products Id Not Found On this UserID : " + productId));
//		
//		products.setUser(user);
//		user.getProductId().add(products);
//		pUserRepositary.save(products);
//		
//		return userDto;
//	}
		
}



