package com.system.service;

import com.system.model.Admin;

public interface AdminService {
	
	Admin signUp(Admin admin);
	Admin register(Admin admin);
	Admin getById(long aId);
	

}
