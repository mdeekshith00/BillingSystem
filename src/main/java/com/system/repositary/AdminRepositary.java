package com.system.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.model.Admin;

public interface AdminRepositary extends JpaRepository<Admin, Integer>{
	
	Optional<Admin> findByUsername(String username);
		
}
