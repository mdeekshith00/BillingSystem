package com.system.serviceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.exception.ReourceNotFoundException;
import com.system.model.Admin;
import com.system.model.Billing;
import com.system.model.BillingItem;
import com.system.model.UserProducts;
import com.system.model.Users;
import com.system.repositary.BillingItemRepositary;
import com.system.repositary.BillingRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.BillingItemService;


@Service
public class BillingItemServiceImpl  implements BillingItemService {
	
	@Autowired
	private BillingItemRepositary billingItemRepositary;
	@Autowired
	private UsersRepositary bUsersRepositary;
	@Autowired
	private BillingRepositary bBillingRepositary;
	
	
		

	@Override
	public BillingItem addBillingItem(BillingItem billingItem) {
		// TODO Auto-generated method stub
		return billingItemRepositary.save(billingItem);
	}


	@Override
	public BillingItem getBiilItemById(Integer itemId) {
		// TODO Auto-generated method stub
		return billingItemRepositary.findById(itemId).orElseThrow(() ->
		new ReourceNotFoundException("BillingItems Id Not Found On this ID:" +itemId ));
	}

	@Override
	public List<BillingItem> getAllBillingItems() {
		// TODO Auto-generated method stub
		return billingItemRepositary.findAll();
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
         
//         Admin admin = new Admin();
//         billing.setAdmin(admin);
//         billing = bBillingRepositary.save(billing); 
         
         List<BillingItem> billingItems = new ArrayList<>();
         BigDecimal grandTotal = BigDecimal.ZERO;

         for (UserProducts userProduct : userProducts) {
             BigDecimal price = userProduct.getMrp();
             int quantity = userProduct.getQuantity();
             BigDecimal totalAmount = price.multiply(BigDecimal.valueOf(quantity));

             // Create BillingItem
             BillingItem item = new BillingItem();
             item.setPrice(price);
             item.setQuantity(quantity);
             item.setTotalAmount(totalAmount);
             item.setCreatedAt(LocalDateTime.now());
             item.setBilling(billing); 
             item.setUserProductId(List.of(userProduct));

             userProduct.setBillingItem(item); 

             billingItems.add(item);

             grandTotal = grandTotal.add(totalAmount);
         }

       
         billing.setItemId(billingItems);

         billingItemRepositary.saveAll(billingItems); 

         return "Total Bill for user " + user.getUName() + ": ₹" + grandTotal;
     }



}

	
//public String generateBil(Integer uId) {
//// TODO Auto-generated method stub
//  Users user = bUsersRepositary.findById(uId)
//          .orElseThrow(() -> new ReourceNotFoundException("User ID not found: " + uId));
//
//  List<UserProducts> unbilledProducts = user.getUserProducts().stream()
//          .filter(p -> p.getBillingItem() == null)
//          .toList();
//
//  if (unbilledProducts.isEmpty()) {
//      return "No unbilled products found for the user.";
//  }
//
//  // Create new BillingItem
//  BillingItem billingItem = new BillingItem();
//  billingItem.setCreatedAt(LocalDateTime.now());
//
//  int totalQuantity = 0;
//  BigDecimal totalAmount = BigDecimal.ZERO;
//  BigDecimal unitPrice = BigDecimal.ZERO; 
//
//  for (UserProducts up : unbilledProducts) {
//      int qty = up.getQuantity();
//      BigDecimal price = up.getMrp();
//
//      totalQuantity += qty;
//      unitPrice = price; 
//      totalAmount = totalAmount.add(price.multiply(BigDecimal.valueOf(qty)));
//
//      up.setBillingItem(billingItem); 
//  }
//
//  billingItem.setPrice(unitPrice); 
//  billingItem.setQuantity(totalQuantity);
//  billingItem.setTotalAmount(totalAmount);
//  billingItem.setUserProductId(unbilledProducts);
//
//  billingItemRepositary.save(billingItem); 
//
//  return "Cart Bill generated for user: " + totalAmount;
//}

