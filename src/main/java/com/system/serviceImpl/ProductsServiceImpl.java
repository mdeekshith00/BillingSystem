package com.system.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.ProductsDto;
import com.system.exception.ProductNotFoundException;
import com.system.model.Products;
import com.system.model.Users;
import com.system.modeldto.ProductsModelDto;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.ProductsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductsServiceImpl implements ProductsService {
	
	
    private final ProductsRepositary prodRepositary;
	
	private final ModelMapper modelMapper;
	
	
	@Override
	public ProductsDto addProducts(ProductsModelDto productsModelDto) {
		// TODO Auto-generated method stub
		
	   Products product =  modelMapper.map(productsModelDto, Products.class);
	   prodRepositary.save(product);
	   ProductsDto productdto = modelMapper.map(product, ProductsDto.class);
	   return productdto;
	}

	@Override
	public List<ProductsDto> getAllProducts() {
		// TODO Auto-generated method stub
		List<Products> products  =  prodRepositary.findAll();
		List<ProductsDto> productList = products.stream().map(a ->modelMapper.map(a,ProductsDto.class)).toList();
		return productList;
	}

	@Override
	public ProductsDto getProdutsById(Integer productId) {
		// TODO Auto-generated method stub
		
		Products product = prodRepositary.findById(productId).orElseThrow(() -> 
		new ProductNotFoundException("Product Not Found on This Id:" + productId));
		
//		productsDto.setPName(p.getProductName());
//		productsDto.setPCompany(p.getProductCompany());
//		productsDto.setMRP(p.getMRP());
//		productsDto.setExpiryDate(p.getExpiryDate());
//		productsDto.setUser(p.getUser());
		
		
		return modelMapper.map(product, ProductsDto.class);
		
		
		
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


