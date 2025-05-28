package com.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.serviceImpl.UserProductsServiceImpl;

@RestController
@RequestMapping("/user-product")
public class UserProductsController {
	
	@Autowired
	private UserProductsServiceImpl userProductService;
	
	@PostMapping("/assign")
  public ResponseEntity<String> assignProductToUser(@RequestParam Integer userId,  @RequestParam Integer productId,
		                                               @RequestParam Integer quantity) {
		return new ResponseEntity<String>(userProductService.assignProductToUser(userId, productId, quantity) , HttpStatus.OK);
	}

}
