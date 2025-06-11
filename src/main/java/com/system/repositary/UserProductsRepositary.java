package com.system.repositary;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.model.Products;
import com.system.model.UserProducts;

public interface UserProductsRepositary extends JpaRepository<UserProducts, Integer> {


		List<UserProducts> findByProduct_ProductIdIn(Set<Integer> productIds);
		List<UserProducts> findByProduct(Products product);
	
	
}
