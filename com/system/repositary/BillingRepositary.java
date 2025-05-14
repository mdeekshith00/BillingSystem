package com.system.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.model.Billing;
import com.system.model.Users;

public interface BillingRepositary extends JpaRepository<Billing, Long>{

	Billing findByUser(Users user);
//	@Query("SELECT SUM(b.bAmount) FROM Billing b")
//    Double sumAmount();


}
