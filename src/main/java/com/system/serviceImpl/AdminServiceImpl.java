package com.system.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.exception.ReourceNotFoundException;
import com.system.model.Admin;
import com.system.repositary.AdminRepositary;
import com.system.service.AdminService;

@Service
public class AdminServiceImpl implements  UserDetailsService , AdminService   {
	
	@Autowired
	private AdminRepositary adminRepositary;
	@Autowired
	private JWTService jwtservice;
	@Autowired
	AuthenticationManager authManager;
	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Admin admin = adminRepositary.findByUsername(username)
	        .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + username));

	    return new org.springframework.security.core.userdetails.User(
	        admin.getUsername(),
	        admin.getPassword(),
	        admin.getAuthorities()  // This already returns List<GrantedAuthority>
	    );
	}





	
	@Override
	@PreAuthorize("Admin")
	public Admin register(Admin admin) {
		// TODO Auto-generated method stub
		
		admin.setUsername(admin.getUsername());
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		admin.setRole(admin.getRole());
		return adminRepositary.save(admin);
	}

	@Override
	public Admin signUp(Admin admin) {
		// TODO Auto-generated method stub
		admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
		return adminRepositary.save(admin);
	}

	@Override
	public Admin getById(Integer aId) {
		// TODO Auto-generated method stub
		return adminRepositary.findById(aId).orElseThrow(() -> 
		new ReourceNotFoundException("Admin Id Not ound On This AdminId:" + aId));
	}

	@Override	
	public String verify(Admin admin) {
		// TODO Auto-generated method stub
		Authentication authentication  = 
		authManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));
		
         if(authentication.isAuthenticated())
//        	 return "success";
         return jwtservice.generateToken(admin.getUsername());

         return "fail";


	}
	@Override
	public Optional findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
