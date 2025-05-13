package com.system.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.system.exception.ResourceNotFound;
import com.system.model.Products;
import com.system.model.Users;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UsersRepositary;

@Service
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
    private ProductsRepositary prodRepositary;
	@Autowired
	private UsersRepositary uProductRepositary;
	
	
	@Override
	public Products addProducts(Products products) {
		// TODO Auto-generated method stub
		return prodRepositary.save(products);
	}

	@Override
	public List<Products> getAllProducts() {
		// TODO Auto-generated method stub
		return prodRepositary.findAll();
	}

	@Override
	public Products getProdutsById(long pId) {
		// TODO Auto-generated method stub
		return prodRepositary.findById(pId).orElseThrow(() -> new ResourceNotFound("Products","Id", pId));
	}

	public List<Products> findByUserId(int uId) {
		// TODO Auto-generated method stub
		Users u = uProductRepositary.findById(uId).orElseThrow(() -> 
		new ResourceNotFound("Products", "Id", uId));
		List<Products> list = new ArrayList<Products>();
		if(u.getUId() == uId)  
			list = prodRepositary.findAll();
		
		return list;
			
	}

	

	

}
