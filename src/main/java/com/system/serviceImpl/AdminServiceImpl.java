package com.system.serviceImpl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.system.dto.AdminDto;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Admin;
import com.system.modeldto.AdminModelDto;
import com.system.repositary.AdminRepositary;
import com.system.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements  UserDetailsService , AdminService   {
	
	
	private final AdminRepositary adminRepositary;
	
	private final JWTService jwtservice;
	@Autowired
	 AuthenticationManager authManager;
	
	private final ModelMapper modelMapper;
	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	    Admin admin = adminRepositary.findByUserName(userName)
	        .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + userName));

	    return new org.springframework.security.core.userdetails.User(
	        admin.getUsername(),
	        admin.getPassword(),
	        admin.getAuthorities()  // This already returns List<GrantedAuthority>
	    );
	}

	@Override
	@PreAuthorize("Admin")
	public AdminDto register(AdminModelDto adminModelDto) {
		// TODO Auto-generated method stub
		
		Admin admin =new Admin();
		
		admin.setUserName(adminModelDto.getUserName());
		admin.setPassword(bCryptPasswordEncoder.encode(adminModelDto.getPassword()));
		admin.setRole(adminModelDto.getRole());
		admin=adminRepositary.save(admin);
		
		return modelMapper.map(admin, AdminDto.class) ;
	}

	@Override
	public AdminDto signUp(AdminModelDto adminModelDto) {
		// TODO Auto-generated method stub

		Admin admin = new Admin();
		admin.setUserName(adminModelDto.getUserName());
		admin.setPassword(bCryptPasswordEncoder.encode(adminModelDto.getPassword()));
		admin.setRole(adminModelDto.getRole());
		
		admin=adminRepositary.save(admin);
		
		return  modelMapper.map(admin, AdminDto.class);
	}

	@Override
	public AdminDto getById(Integer aId) {
		// TODO Auto-generated method stub
		
		Admin admin =  adminRepositary.findById(aId).orElseThrow(() -> 
		new ReourceNotFoundException("Admin Id Not Found On This AdminId:" + aId));
		
		return modelMapper.map(admin, AdminDto.class);
		
	}

	@Override	
	public String verify(AdminModelDto adminModelDto) {
		// TODO Auto-generated method stub
		Admin admin = modelMapper.map(adminModelDto, Admin.class);
		
		Authentication authentication  = 
		authManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));
		
         if(authentication.isAuthenticated()) {
         return jwtservice.generateToken(admin.getUsername());
         }
         return "fail";
	}
	
	@Override
	public Optional findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
