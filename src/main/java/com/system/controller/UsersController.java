
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
	
	@PostMapping("/add")
	public ResponseEntity<Users> addUser(@RequestBody  Users user) {
		System.out.println(uService.addUser(user));
		return new ResponseEntity<Users>(uService.addUser(user) , HttpStatus.CREATED);
	}
	@GetMapping
	public ResponseEntity<List<Users>> getAllUsers(){
		return new ResponseEntity<>(uService.getAllUsers() , HttpStatus.OK);
	}
	@GetMapping("/details")
	public ResponseEntity<Users> getUserById(@RequestParam Integer uId , @RequestParam Integer bId) {
		return new ResponseEntity<Users>(uService.getUserById(uId, bId) , HttpStatus.OK);
		
	}
	@GetMapping("/id/{uId}")
	public ResponseEntity<Users> getUserById(@PathVariable Integer uId){
		return new ResponseEntity<Users>(uService.getUserById(uId) , HttpStatus.OK);
	}
	
//	@PostMapping("/user/{uId}/products/{pId}")
//	public ResponseEntity<UsersDto> setProductsToUsers(@PathVariable Integer uId,@PathVariable Integer pId) {
//		return new ResponseEntity<UsersDto>(uService.setProductsToUsers(uId, pId) ,  HttpStatus.OK);
//	}

}



