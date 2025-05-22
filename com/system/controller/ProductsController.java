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

import com.system.dto.ProductsDto;
import com.system.model.Products;
import com.system.serviceImpl.ProductsServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductsServiceImpl prodservice;
	
	@PostMapping("/addproduct")
	public ResponseEntity<ProductsDto> addProducts(@RequestBody Products products) {
		return new ResponseEntity<>(prodservice.addProducts(products)  ,HttpStatus.CREATED);
	}
	
	@GetMapping("/{pid}")
	public ResponseEntity<ProductsDto> getProdutsById(@PathVariable(name = "pid") long pId) {
		return new ResponseEntity<>(prodservice.getProdutsById(pId)  ,HttpStatus.OK);

		
	}
	@GetMapping
	public ResponseEntity<List<ProductsDto>> getAllProducts(){
		return new ResponseEntity<>(prodservice.getAllProducts()  ,HttpStatus.OK);
	}
	
	@GetMapping("/user/{uid}")
	public ResponseEntity<List<Products>> findByUserId(@PathVariable(name = "uid") long uId ){
		return new ResponseEntity<>(prodservice.findByUserId(uId)   , HttpStatus.OK);
	}
	
	
	

}
