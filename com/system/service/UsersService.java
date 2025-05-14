package com.system.service;

import java.util.List;

import com.system.model.Users;

public interface UsersService {
	
	Users addUser(Users user);
	Users getUserById(long uId);
	List<Users> getAllUsers();

   Users setProductsToUsers(long uId , long pId);
	
	
	
	
}
