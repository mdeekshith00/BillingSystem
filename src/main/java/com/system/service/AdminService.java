package com.system.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

import com.system.model.Admin;

public interface AdminService {
	
     String verify(Admin admin);
	Admin signUp(Admin admin);
	Admin register(Admin admin);
	Admin getById(Integer aId);
	Optional<Admin> findByUsername(String username);
	

}
