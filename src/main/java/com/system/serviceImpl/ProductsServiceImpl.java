package com.system.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.ProductsDto;
import com.system.exception.ProductNotFoundException;
import com.system.model.Products;
import com.system.model.Users;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
    private ProductsRepositary prodRepositary;
	@Autowired
	private UsersRepositary uProductRepositary;
	
	@Autowired
	private ProductsDto productsDto;
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public Products addProducts(Products products) {
		// TODO Auto-generated method stub
	   Products p =  prodRepositary.save(products);
//	   ProductsDto productdto = modelMapper.map(p, ProductsDto.class);
	   return p;
	}

	@Override
	public List<Products> getAllProducts() {
		// TODO Auto-generated method stub
		List<Products> p  =  prodRepositary.findAll();
//		List<ProductsDto> dtoList = p.stream().map(a ->modelMapper.map(a,ProductsDto.class)).toList();
		return p;
	}

	@Override
	public Products getProdutsById(Integer productId) {
		// TODO Auto-generated method stub
		
		Products p = prodRepositary.findById(productId).orElseThrow(() -> 
		new ProductNotFoundException("Product Not Found on This Id:" + productId));
		
//		productsDto.setPName(p.getProductName());
//		productsDto.setPCompany(p.getProductCompany());
//		productsDto.setMRP(p.getMRP());
//		productsDto.setExpiryDate(p.getExpiryDate());
//		productsDto.setUser(p.getUser());
		
		return p;
		
		
		
	}

//	public List<Products> findByUserId(Integer uId) {
//		// TODO Auto-generated method stub
//		Users u = uProductRepositary.findById(uId).orElseThrow(() -> 
//		new ProductNotFoundException("Product Not found On this UserId:" + uId));
//		
//		 List<Products> userProducts = u.getProductId(); 
//			return userProducts;
//	}

	
}


