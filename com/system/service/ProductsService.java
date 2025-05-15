package com.system.service;

import java.util.List;

import com.system.dto.ProductsDto;
import com.system.model.Products;



public interface ProductsService {
	ProductsDto addProducts(Products products);
	List<ProductsDto> getAllProducts();
	ProductsDto getProdutsById(long pId);
	
	List<Products> findByUserId(long uId);

	

}
