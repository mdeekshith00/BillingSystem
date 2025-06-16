package com.system.service;

import java.util.List;

import com.system.dto.UserDtoMin;
import com.system.dto.UsersBillDto;
import com.system.dto.UsersDto;
import com.system.dto.UsersDto2;
import com.system.dto.UsersDtoMax;
import com.system.modeldto.UsersModelDto;

public interface UsersService {
	
	UserDtoMin addUser(UsersModelDto userModelDto);
	UsersDto2 getUserById(Integer uId);
	UsersBillDto getBillByUserId(Integer uId, Integer bId);
	List<UsersDto2> getAllUsers();
	UsersDtoMax getUserDetailsById(Integer uId);

}
