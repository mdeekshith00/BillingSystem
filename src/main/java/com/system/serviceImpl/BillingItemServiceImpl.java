package com.system.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.BillingItemDto;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Billing;
import com.system.model.BillingItem;
import com.system.model.UserProducts;
import com.system.model.Users;
import com.system.modeldto.BillingItemModelDto;
import com.system.repositary.BillingItemRepositary;
import com.system.repositary.BillingRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.BillingItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BillingItemServiceImpl  implements BillingItemService {
	
	
	private final  BillingItemRepositary billingItemRepositary;
	
	private final  UsersRepositary bUsersRepositary;
	
	private final BillingRepositary bBillingRepositary;
	
	private final  ModelMapper modelMapper;
	
	
		

	@Override
	public BillingItemDto addBillingItem(BillingItemModelDto billingItemModelDto) {
		// TODO Auto-generated method stub
		BillingItem billingItem = modelMapper.map(billingItemModelDto, BillingItem.class);
		 billingItemRepositary.save(billingItem);
		 
		 return modelMapper.map(billingItem, BillingItemDto.class);
	}


	@Override
	public BillingItemDto getBiilItemById(Integer itemId) {
		// TODO Auto-generated method stub
		BillingItem billingItem = billingItemRepositary.findById(itemId).orElseThrow(() ->
		new ReourceNotFoundException("BillingItems Id Not Found On this ID:" +itemId ));
		
		return modelMapper.map(billingItem, BillingItemDto.class);
	}

	@Override
	public List<BillingItemDto> getAllBillingItems() {
		// TODO Auto-generated method stub
		List<BillingItem> itemList =  (List<BillingItem>) billingItemRepositary.findAll();
		List<BillingItemDto> itemDtoList = itemList.stream()
				.map(a -> modelMapper.map(itemList, BillingItemDto.class)).toList();
		return itemDtoList;
	}

     @Override
     public String generateFullBillForUser(Integer uId) {
         Users user = bUsersRepositary.findById(uId)
             .orElseThrow(() -> new ReourceNotFoundException("User ID not found: " + uId));

         List<UserProducts> userProducts = user.getUserProducts();

         if (userProducts == null || userProducts.isEmpty()) {
             throw new ReourceNotFoundException("No products found in cart for user ID: " + uId);
         }
         Billing billing = new Billing();
         billing.setUser(user);
         billing = bBillingRepositary.save(billing); 

         
         List<BillingItem> billingItems = new ArrayList<>();
         BigDecimal grandTotal = BigDecimal.ZERO;

         for (UserProducts userProduct : userProducts) {
             BigDecimal price = userProduct.getMrp();
             int quantity = userProduct.getQuantity();
             BigDecimal totalAmount = price.multiply(BigDecimal.valueOf(quantity));
             
             BillingItem item = new BillingItem();
             item.setPrice(price);
             item.setQuantity(quantity);
             item.setTotalAmount(totalAmount);
             item.setCreatedAt(LocalDateTime.now());
             item.setBilling(billing); 
             item.setUserProducts(List.of(userProduct));

             userProduct.setBillingItem(item); 

             billingItems.add(item);

             grandTotal = grandTotal.add(totalAmount);
             }
       
         billing.setItemId(billingItems);

         billingItemRepositary.saveAll(billingItems); 

         return "Total Bill for user " + user.getUName() + ": ₹" + grandTotal;
     }

}

