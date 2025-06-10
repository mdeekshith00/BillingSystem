package com.system.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.model.BillingItem;
import com.system.model.UserProducts;

public interface BillingItemRepositary extends JpaRepository<BillingItem, Integer>{
	List<BillingItem> findByuserProductsIn(List<UserProducts> userProducts); // UserProductId

}
