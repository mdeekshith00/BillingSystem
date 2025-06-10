package com.system.repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.model.Billing;
import com.system.model.BillingItem;
import com.system.model.Users;

public interface BillingRepositary extends JpaRepository<Billing, Integer>{

	Billing findByUser(Users user);
   List<Billing> findByitemIdIn(List<BillingItem> billingItem);


}
