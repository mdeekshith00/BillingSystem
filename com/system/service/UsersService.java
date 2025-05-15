package com.system.service;

import java.util.List;

import com.system.dto.UsersDto;
import com.system.model.Users;

public interface UsersService {
	
	UsersDto addUser(Users user);
//	Users addUser(Users user , long pId);
	UsersDto getUserById(long uId, long bId);
	List<UsersDto> getAllUsers();

   UsersDto setProductsToUsers(long uId , long pId);
	
	
	
	
}
