package com.system.service;

import java.util.List;

import com.system.dto.UsersDto;
import com.system.model.Users;

public interface UsersService {
	
	UsersDto addUser(Users user);
//	Users addUser(Users user , long pId);
	UsersDto getUserById(Integer uId, Integer bId);
	List<UsersDto> getAllUsers();

   UsersDto setProductsToUsers(Integer uId , Integer pId);
	
	
	
	
}
