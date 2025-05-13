package com.system.service;

import java.util.List;

import com.system.model.Users;

public interface UsersService {
	
	Users addUser(Users user);
	Users getUserById(int uId);
	List<Users> getAllUsers();


	
	
	
	

}
