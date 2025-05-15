package com.system.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.exception.ResourceNotFound;
import com.system.model.Admin;
import com.system.repositary.AdminRepositary;
import com.system.service.AdminService;

@Service
public class AdminServiceImpl implements  UserDetailsService , AdminService   {
	
	@Autowired
	private AdminRepositary adminRepositary;
	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return adminRepositary.findByName(username);
	}

	@Override
	public Admin register(Admin admin) {
		// TODO Auto-generated method stub
		admin.setName(admin.getName());
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		admin.setRole(admin.getRole());
		
		return adminRepositary.save(admin);
	}
	

	@Override
	public Admin getById(long aId) {
		// TODO Auto-generated method stub
		return adminRepositary.findById(aId).orElseThrow(() -> 
		new ResourceNotFound("Admin", "aId", aId));
	}

	@Override
	public Admin signUp(Admin admin) {
		// TODO Auto-generated method stub
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		return adminRepositary.save(admin);
	}

}
