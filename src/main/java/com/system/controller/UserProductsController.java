package com.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.dto.UserProductsDto;
import com.system.model.UserProducts;
import com.system.serviceImpl.UserProductsServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/user-product")
public class UserProductsController {
	
	
	private final UserProductsServiceImpl userProductService;
	
//	@PostMapping("/assign")
//  public ResponseEntity<String> assignProductToUser(@RequestParam Integer userId, @Valid @RequestParam Integer productId,
//		  @Valid  @RequestParam Integer quantity) {
//		return new ResponseEntity<>(userProductService.assignProductToUser(userId, productId, quantity) , HttpStatus.OK);
//	}
	@GetMapping
	public  ResponseEntity<List<UserProductsDto>> getAllUserProducts(){
		return new ResponseEntity<>(userProductService.getAllUserProducts()  , HttpStatus.OK);
	}

}
