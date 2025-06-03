package com.system.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.system.dto.AdminDto;
import com.system.model.Admin;
import com.system.modeldto.AdminModelDto;

public interface AdminService {
	
     String verify(AdminModelDto adminModelDto);
	AdminDto signUp(AdminModelDto adminModelDto);
	AdminDto register(AdminModelDto adminModelDto);
	
	AdminDto getById(Integer aId);
	Optional<Admin> findByUsername(String userName);
	

}
