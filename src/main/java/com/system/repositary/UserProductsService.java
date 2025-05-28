package com.system.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.model.UserProducts;

public interface UserProductsService extends JpaRepository<UserProducts, Integer> {

}
