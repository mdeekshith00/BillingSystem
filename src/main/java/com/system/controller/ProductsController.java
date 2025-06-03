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
import com.system.modeldto.ProductsModelDto;
import com.system.serviceImpl.ProductsServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductsServiceImpl prodservice;
	
	@PostMapping("/addproduct")
	public ResponseEntity<ProductsDto> addProducts(@RequestBody ProductsModelDto productsModelDto) {
		return new ResponseEntity<>(prodservice.addProducts(productsModelDto)  ,HttpStatus.CREATED);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductsDto> getProdutsById(@PathVariable Integer productId) {
		return new ResponseEntity<>(prodservice.getProdutsById(productId)  ,HttpStatus.OK);

		
	}
	@GetMapping
	public ResponseEntity<List<ProductsDto>> getAllProducts(){
		return new ResponseEntity<>(prodservice.getAllProducts()  ,HttpStatus.OK);
	}
	
}
