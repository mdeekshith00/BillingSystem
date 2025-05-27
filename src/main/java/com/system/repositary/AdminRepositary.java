package com.system.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.model.Admin;

public interface AdminRepositary extends JpaRepository<Admin, Integer>{
	Admin findByName(String name);

}
