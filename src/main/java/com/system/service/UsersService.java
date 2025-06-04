package com.system.service;

import java.util.List;

import com.system.dto.UsersDto;
import com.system.dto.UsersDto1;
import com.system.dto.UsersDto2;
import com.system.modeldto.UsersModelDto;

public interface UsersService {
	
	UsersDto addUser(UsersModelDto userModelDto);
	UsersDto2 getUserById(Integer uId);
	UsersDto1 getBillByUserId(Integer uId, Integer bId);
	List<UsersDto2> getAllUsers();
	
	UsersDto1 setProductsToUsers(Integer uId, Integer productId, int quantity);
	
	UsersDto getUserDetailsById(Integer uId);
  
	
	
	
	
}
