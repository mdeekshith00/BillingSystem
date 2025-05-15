
package com.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.dto.UsersDto;
import com.system.model.Users;
import com.system.serviceImpl.UsersServiceImpl;



@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UsersServiceImpl uService;
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsersDto> addUser(@RequestBody  Users user) {
		System.out.println(uService.addUser(user));
		return new ResponseEntity<UsersDto>(uService.addUser(user) , HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<UsersDto>> getAllUsers(){
		return new ResponseEntity<>(uService.getAllUsers() , HttpStatus.OK);
	}
	@GetMapping("/details")
	public ResponseEntity<UsersDto> getUserById(@RequestParam(value ="uId") long uId , @RequestParam(value ="bId") long bId) {
		return new ResponseEntity<UsersDto>(uService.getUserById(uId, bId) , HttpStatus.OK);
		
	}
	
	@PostMapping("/user/{uId}/products/{pId}")
	public ResponseEntity<UsersDto> setProductsToUsers(@PathVariable long uId,@PathVariable long pId) {
		return new ResponseEntity<UsersDto>(uService.setProductsToUsers(uId, pId) ,  HttpStatus.OK);
	}

}



