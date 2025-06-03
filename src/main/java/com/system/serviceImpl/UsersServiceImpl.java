package com.system.serviceImpl;

import java.util.List;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.UsersDto;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Billing;
import com.system.model.BillingItem;
import com.system.model.Products;
import com.system.model.UserProducts;
import com.system.model.Users;
import com.system.modeldto.UsersModelDto;
import com.system.repositary.BillingRepositary;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UserProductsRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {
	
	
	private final UsersRepositary userRepositary;
	
	private final ProductsRepositary pUserRepositary;
	
	private final BillingRepositary bUserRepositary;
	
	private final UserProductsRepositary upUserRepositary;
	
	private final ModelMapper modelMapper;
	


	@Override
	public UsersDto addUser(UsersModelDto userModelDto) {
		// TODO Auto-generated method stub
		
		Users user = modelMapper.map(userModelDto, Users.class);
		userRepositary.save(user);

		UsersDto u1 = modelMapper.map(user,UsersDto.class);
		return u1;
	}
 
//	 get user and billing  details using thier ids
	@Override
	public UsersDto getBillByUserId(Integer uId , Integer bId) {
		// TODO Auto-generated method stub
		Users u =  userRepositary.findById(uId).orElseThrow(() -> 
		new ReourceNotFoundException("User Id Not Found On this UserID : " + uId));
		
		Billing b = bUserRepositary.findById(bId).orElseThrow(() -> 
		new ReourceNotFoundException("Billing Id Not Found On this UserID : " + bId));
		
		List<Products> p = pUserRepositary.findAll();
		UsersDto userDto = new UsersDto();
		userDto.setUName(u.getUName());
		userDto.setMobileNo(u.getMobileNo());
		userDto.setEMail(u.getEMail());
		

		// billing 
		userDto.setBId(b.getBId());		
		
		UsersDto userDto1 = modelMapper.map(u, UsersDto.class);
		return userDto1;
		
	}

	@Override
	public List<UsersDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<Users> list =  userRepositary.findAll();
		List<UsersDto> list1 = list.stream().map(a -> modelMapper.map(a , UsersDto.class)).toList();
		return list1;
	}

	@Override
	public UsersDto getUserById(Integer uId) {
		// TODO Auto-generated method stub
		Users user =  userRepositary.findById(uId).orElseThrow(() -> 
		new ReourceNotFoundException("User Id Not Found On this UserID : " + uId));
		
		UsersDto u =  modelMapper.map(user, UsersDto.class);
		return u;
	}

	
	@Override
	public UsersDto setProductsToUsers(Integer uId, Integer productId, int quantity) {
	    Users user = userRepositary.findById(uId)
	        .orElseThrow(() -> new ReourceNotFoundException("User Id Not Found: " + uId));

	    Products product = pUserRepositary.findById(productId)
	        .orElseThrow(() -> new ReourceNotFoundException("Product Id Not Found: " + productId));
	    

	    Integer check = product.getQuantityAvailable();
	
	    if(check > quantity) {
	    UserProducts userProduct = new UserProducts();
	
	    userProduct.setUser(user);
	    userProduct.setProduct(product);
	    userProduct.setMrp(product.getMRP());
	    userProduct.setQuantity(quantity);

	    user.getUserProducts().add(userProduct);

	
	    upUserRepositary.save(userProduct);
	    
	    product.setQuantityAvailable(check-quantity);  
	    pUserRepositary.save(product);
	    
	    }
	    else {
	     new ReourceNotFoundException("Product quantity is Not Avaliable on this Store ,"
	     		+ " please select Other Products" + uId);
	    }
	   
	    return modelMapper.map(user, UsersDto.class);
	}



	// model mapper is not working , so i choose  manual to retrive data using userDto
	@Override
	public UsersDto getUserDetailsById(Integer uId) {
	    Users u = userRepositary.findById(uId)
	        .orElseThrow(() -> new ReourceNotFoundException("User Id Not Found On this UserID : " + uId));

	    UsersDto userDto = new UsersDto();

	    // Set basic user fields
	    userDto.setUName(u.getUName());
	    userDto.setMobileNo(u.getMobileNo());
	    userDto.setEMail(u.getEMail());

	    // Set user products
	    List<UserProducts> userProducts = u.getUserProducts();
	    userDto.setProductList(userProducts);

	    if (userProducts != null && !userProducts.isEmpty()) {
	        UserProducts firstProduct = userProducts.get(0);
	        userDto.setMrp(firstProduct.getMrp());
	        userDto.setUquantity(firstProduct.getQuantity());

	        Products product = firstProduct.getProduct();
	        if (product != null) {
	            userDto.setPId(product.getProductId());
	            userDto.setPName(product.getProductName());
	            userDto.setPCompany(product.getProductCompany());
	            userDto.setMrp(product.getMRP());
	            userDto.setExpiryDate(product.getExpiryDate());
	        }
	    }

	    // Set billing details
	    List<Billing> billingList = u.getBId();
	    if (billingList != null && !billingList.isEmpty()) {
	        Billing latestBilling = billingList.get(0);
	        userDto.setBId(latestBilling.getBId());

	        List<BillingItem> items = latestBilling.getItemId();
	        if (items != null && !items.isEmpty()) {
	            BillingItem firstItem = items.get(0);
	            userDto.setItemId(firstItem.getItemId());
	            userDto.setQuantity(firstItem.getQuantity());
	            userDto.setPrice(firstItem.getPrice());
	            userDto.setTotalAmount(firstItem.getTotalAmount());
	            userDto.setCreatedAt(firstItem.getCreatedAt());
	        }
	    }

	    return userDto;
	}


	// model mapper is not working 
//	@Override
//	public UsersDto getUserDetailsById(Integer uId) {
//		// TODO Auto-generated method stub
//		Users u =  userRepositary.findById(uId).orElseThrow(() -> 
//		new ReourceNotFoundException("User Id Not Found On this UserID : " + uId));
//		
//		UsersDto userdto = modelMapper.map(u, UsersDto.class);
//		
//		  userdto.setProductList(u.getUserProducts());
//	
//		  List<Billing> billingList = u.getBId();
//		    if (billingList != null && !billingList.isEmpty()) {
//		        Billing latestBilling = billingList.get(0); 
//		        userdto.setBId(latestBilling.getBId());
//		        
//		        List<BillingItem> items = latestBilling.getItemId();
//		        if (items != null && !items.isEmpty()) {
//		            BillingItem firstItem = items.get(0);
//		            userdto.setItemId(firstItem.getItemId());
//		            userdto.setQuantity(firstItem.getQuantity());
//		            userdto.setPrice(firstItem.getPrice());
//		            userdto.setTotalAmount(firstItem.getTotalAmount());
//		            userdto.setCreatedAt(firstItem.getCreatedAt());
//		        }
//		    }
//		
//		return userdto;
//	}

		
}



