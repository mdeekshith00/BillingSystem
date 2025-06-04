package com.system.service;

import java.util.List;

import com.system.dto.ProductsDto;
import com.system.modeldto.ProductsModelDto;

public interface ProductsService {
	ProductsDto addProducts(ProductsModelDto productsModelDto);
	List<ProductsDto> getAllProducts();
	ProductsDto getProdutsById(Integer productId);
	

	

}
