package com.system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.dto.ProductsDto;
import com.system.modeldto.ProductsModelDto;
import com.system.serviceImpl.ProductsServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/products")
public class ProductsController {

	private final ProductsServiceImpl prodservice;
	
	@PostMapping("/addproduct")
	public ResponseEntity<ProductsDto> addProducts(@Valid @RequestBody ProductsModelDto productsModelDto) {
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