package com.system.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import com.system.model.BillingItem;

public interface BillingItemRepositary extends JpaRepository<BillingItem, Integer>{

}
