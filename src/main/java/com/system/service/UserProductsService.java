package com.system.service;

import java.util.List;

import com.system.model.UserProducts;

public interface UserProductsService {
	 List<UserProducts> getAllUserProducts();
	 String assignProductToUser( Integer userId,  Integer productId,Integer quantity);

}
