package com.system.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.model.Products;

public interface ProductsRepositary extends JpaRepository<Products, Integer>{
	
	List<Products> findByProductName(String productName);
	List<Products> findByProductCompany(String productCompany);
	
	
}
