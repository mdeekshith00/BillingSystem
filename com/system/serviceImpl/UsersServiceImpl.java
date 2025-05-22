package com.system.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.UsersDto;
import com.system.exception.ResourceNotFound;
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
	public UsersDto addUser(Users user) {
		// TODO Auto-generated method stub
		Users u = userRepositary.save(user);
		UsersDto u1 = modelMapper.map(u,UsersDto.class);
		return u1;
	}

	@Override
	public UsersDto getUserById(long uId , long bId) {
		// TODO Auto-generated method stub
		Users u =  userRepositary.findById(uId).orElseThrow(() -> 
		new ResourceNotFound("Users", "Id", uId));
		
		Billing b = bUserRepositary.findById(bId).orElseThrow(() -> 
		new ResourceNotFound("Billing", "Id", uId));
		
		List<Products> p = pUserRepositary.findAll();
		
		userDto.setUName(u.getUName());
		userDto.setMobileNo(u.getMobileNo());
		userDto.setEMail(u.getEMail());
		

		// billing 
		userDto.setBId(b.getBId());		
		userDto.setBAmount(b.getBAmount());
		return userDto;
		
	}

	@Override
	public List<UsersDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<Users> list =  userRepositary.findAll();
		List<UsersDto> list1 = list.stream().map(a ->modelMapper.map(a , UsersDto.class)).toList();
		return list1;
	}

	
	@Override
	public UsersDto setProductsToUsers(long uId, long pId) {
		// TODO Auto-generated method stub
		Users user = userRepositary.findById(uId).orElseThrow(() -> 
		new ResourceNotFound("Users", "Id", uId));
		modelMapper.map(user, UsersDto.class);	
	
		Products products = pUserRepositary.findById(pId).orElseThrow(() -> 
		new ResourceNotFound("Products", "Id", pId));
		
		products.setUser(user);
		user.getPId().add(products);
		pUserRepositary.save(products);
		
		return userDto;
	}
		
}



