package com.system.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.ProductsDto;
import com.system.exception.ProductNotFoundException;
import com.system.exception.ResourceNotFound;
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
	public ProductsDto addProducts(Products products) {
		// TODO Auto-generated method stub
	   Products p =  prodRepositary.save(products);
	   ProductsDto productdto = modelMapper.map(p, ProductsDto.class);
	   return productdto;
	}

	@Override
	public List<ProductsDto> getAllProducts() {
		// TODO Auto-generated method stub
		List<Products> p  =  prodRepositary.findAll();
		List<ProductsDto> dtoList = p.stream().map(a ->modelMapper.map(a,ProductsDto.class)).toList();
		return dtoList;
	}

	@Override
	public ProductsDto getProdutsById(long pId) {
		// TODO Auto-generated method stub
		
		Products p = prodRepositary.findById(pId).orElseThrow(() -> 
		new ProductNotFoundException("Product Not Found on This Id:"));
		
		productsDto.setPName(p.getPName());
		productsDto.setPCompany(p.getPCompany());
		productsDto.setMRP(p.getMRP());
		productsDto.setExpiryDate(p.getExpiryDate());
		productsDto.setUser(p.getUser());
		
		return productsDto;
		
		
		
	}

	public List<Products> findByUserId(long uId) {
		// TODO Auto-generated method stub
		Users u = uProductRepositary.findById(uId).orElseThrow(() -> 
		new ResourceNotFound("Products", "uId", uId));
		
		 List<Products> userProducts = u.getPId(); 
			return userProducts;
	}

	
}


