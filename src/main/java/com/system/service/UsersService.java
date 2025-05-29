package com.system.service;

import java.util.List;

import com.system.dto.UsersDto;
import com.system.model.Users;

public interface UsersService {
	
	Users addUser(Users user);
	Users getUserById(Integer uId);
	Users getUserById(Integer uId, Integer bId);
	List<Users> getAllUsers();
//   UsersDto setProductsToUsers(Integer uId , Integer pId);
	
	
	
	
}
