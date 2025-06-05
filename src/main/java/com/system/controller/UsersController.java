
package com.system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.dto.UsersDto;
import com.system.dto.UsersDto1;
import com.system.dto.UsersDto2;
import com.system.modeldto.UsersModelDto;
import com.system.serviceImpl.UsersServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {
	
	
	private final UsersServiceImpl uService;
	
	@PostMapping("/add")
	public ResponseEntity<UsersDto> addUser(@Valid  @RequestBody  UsersModelDto userModelDto) {
		System.out.println(uService.addUser(userModelDto));
		return new ResponseEntity<>(uService.addUser(userModelDto) , HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<UsersDto2>> getAllUsers(){
		return new ResponseEntity<>(uService.getAllUsers() , HttpStatus.OK);
	}
	@GetMapping("/details")
	public ResponseEntity<UsersDto1> getUserById(@RequestParam Integer uId , @RequestParam Integer bId) {
		return new ResponseEntity<>(uService.getBillByUserId(uId, bId) , HttpStatus.OK);
	}
	@GetMapping("/id/{uId}")
	public ResponseEntity<UsersDto2> getUserById(@PathVariable Integer uId){
		return new ResponseEntity<>(uService.getUserById(uId) , HttpStatus.OK);
	}
	@PostMapping("/assign")
	public ResponseEntity<UsersDto1> setProductsToUsers(@Valid @RequestParam Integer uId,@RequestParam Integer productId, @RequestParam int quantity) {
		return new ResponseEntity<>(uService.setProductsToUsers(uId, productId, quantity) , HttpStatus.OK);
	}
	
	// need to change 
	@GetMapping("/getAllDeatils/{uId}")
	public ResponseEntity<UsersDto> getUserDetailsById(@PathVariable Integer uId) {
		return new ResponseEntity<UsersDto>(uService.getUserDetailsById(uId) , HttpStatus.OK);
	}

}



