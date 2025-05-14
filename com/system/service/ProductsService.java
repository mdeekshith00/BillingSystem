package com.system.service;

import java.util.List;
import com.system.model.Products;



public interface ProductsService {
	Products addProducts(Products products);
	List<Products> getAllProducts();
	Products getProdutsById(long pId);
	
	List<Products> findByUserId(long uId);

	

}
