package com.system.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.system.dto.UsersDto;
import com.system.dto.UsersDto1;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Billing;
import com.system.model.BillingItem;
import com.system.model.Products;
import com.system.model.UserProducts;
import com.system.model.Users;
import com.system.modeldto.UsersModelDto;
import com.system.repositary.BillingItemRepositary;
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
		

		UsersDto userDto = new UsersDto();
		userDto.setUName(u.getUName());
		userDto.setMobileNo(u.getMobileNo());
		userDto.setEMail(u.getEMail());
		// billing 
		userDto.setBId(b.getBId());		
		
		return userDto;
		
	}

	@Override
	public List<UsersDto> getAllUsers() {
		// TODO Auto-generated method stub
		List<Users> list =  userRepositary.findAll();
	
		List<UsersDto> list1 = list.stream().map(user -> {
		    UsersDto dto = new UsersDto();
		    dto.setUId(user.getUId());
		    dto.setUName(user.getUName());
		    return dto;
		}
		).collect(Collectors.toList());
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
	public UsersDto getUserDetailsById(Integer uId) {
	    Users u = userRepositary.findById(uId)
	        .orElseThrow(() -> new ReourceNotFoundException("User Id Not Found On this UserID : " + uId));

	    UsersDto userDto = new UsersDto();

	    // Set basic user fields
	    userDto.setUId(u.getUId());
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
//	            userDto.setPName(product.getProductName());
//	            userDto.setPCompany(product.getProductCompany());
	            userDto.setMrp(product.getMRP());
	            userDto.setExpiryDate(product.getExpiryDate());
	        }
	    }

	    List<Billing> billingList = u.getBId();
	    if (billingList != null && !billingList.isEmpty()) {
	        Billing latestBilling = billingList.get(0);
	        userDto.setBId(latestBilling.getBId());

	        List<BillingItem> items = latestBilling.getItemId();
	        if (items != null && !items.isEmpty()) {
	            BillingItem firstItem = items.get(0);
//	            userDto.setItemId(firstItem.getItemId());
	            userDto.setQuantity(firstItem.getQuantity());
	            userDto.setPrice(firstItem.getPrice());
	            userDto.setTotalAmount(firstItem.getTotalAmount());
	            userDto.setCreatedAt(firstItem.getCreatedAt());
	        }
	    }

	    return userDto;
	}


	@Override
	public UsersDto1 setProductsToUsers(Integer uId, Integer productId, int quantity) {
	    Users user = userRepositary.findById(uId)
	        .orElseThrow(() -> new ReourceNotFoundException("User Id Not Found: " + uId));

	    Products product = pUserRepositary.findById(productId)
	        .orElseThrow(() -> new ReourceNotFoundException("Product Id Not Found: " + productId));

	    int available = product.getQuantityAvailable();

	    if (quantity > available) {
	        throw new ReourceNotFoundException("Product quantity is not available. Requested: " + quantity + ", Available: " + available);
	    }


	    if (user.getUserProducts() == null) {
	        user.setUserProducts(new ArrayList<>());
	    }

	    UserProducts userProduct = new UserProducts();
	    userProduct.setUser(user);
	    userProduct.setProduct(product);
	    userProduct.setMrp(product.getMRP());
	    userProduct.setQuantity(quantity);

	  
	    user.getUserProducts().add(userProduct);

	    upUserRepositary.save(userProduct);

	    product.setQuantityAvailable(available - quantity);
	    pUserRepositary.save(product);

	    UsersDto1 dto = new UsersDto1();
	    dto.setUName(user.getUName());
	    dto.setMobileNo(user.getMobileNo());
	    dto.setEMail(user.getEMail());
	    dto.setUserProducts(List.of(userProduct));
	  

	    return dto;
	}


		
}



