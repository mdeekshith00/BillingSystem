package com.system.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.model.UserProducts;

public interface UserProductsRepositary extends JpaRepository<UserProducts, Integer> {

}
