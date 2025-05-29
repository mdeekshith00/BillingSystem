package com.system.service;

import java.util.List;

import com.system.dto.UsersDto;
import com.system.model.Users;

public interface UsersService {
	
	Users addUser(Users user);
	Users getUserById(Integer uId);
	Users getBillByUserId(Integer uId, Integer bId);
	List<Users> getAllUsers();
	
	Users setProductsToUsers(Integer uId, Integer productId, int quantity);
	
	UsersDto getUserDetailsById(Integer uId);
  
	
	
	
	
}
