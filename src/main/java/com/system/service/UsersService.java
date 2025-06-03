package com.system.service;

import java.util.List;

import com.system.dto.UsersDto;
import com.system.model.Users;
import com.system.modeldto.UsersModelDto;

public interface UsersService {
	
	UsersDto addUser(UsersModelDto userModelDto);
	UsersDto getUserById(Integer uId);
	UsersDto getBillByUserId(Integer uId, Integer bId);
	List<UsersDto> getAllUsers();
	
	UsersDto setProductsToUsers(Integer uId, Integer productId, int quantity);
	
	UsersDto getUserDetailsById(Integer uId);
  
	
	
	
	
}
