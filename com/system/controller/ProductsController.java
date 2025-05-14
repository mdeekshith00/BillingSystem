package com.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.model.Products;
import com.system.service.ProductsServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	
	@Autowired
	private ProductsServiceImpl prodservice;
	
	@PostMapping("/addProduct")
	public ResponseEntity<Products> addProducts(@RequestBody Products products) {
		return new ResponseEntity<>(prodservice.addProducts(products)  ,HttpStatus.CREATED);
	}
	
	@GetMapping("/{pId}")
	public ResponseEntity<Products> getProdutsById(@PathVariable long pId) {
		return new ResponseEntity<>(prodservice.getProdutsById(pId)  ,HttpStatus.OK);

		
	}
	@GetMapping("/allProduct")
	public ResponseEntity<List<Products>> getAllProducts(){
		return new ResponseEntity<>(prodservice.getAllProducts()  ,HttpStatus.OK);
	}
	
	@GetMapping("/user/{uId}")
	public ResponseEntity<List<Products>> findByUserId(@PathVariable long uId ){
		return new ResponseEntity<>(prodservice.findByUserId(uId)   , HttpStatus.OK);
	}
	
	
	

}
