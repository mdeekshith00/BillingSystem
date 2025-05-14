
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

import com.system.model.Users;
import com.system.service.UsersServiceImpl;



@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UsersServiceImpl uService;
	
	@PostMapping("/add")
	public ResponseEntity<Users> addUser(@RequestBody  Users user) {
		System.out.println(uService.addUser(user));
		return new ResponseEntity<Users>(uService.addUser(user) , HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<Users>> getAllUsers(){
		return new ResponseEntity<>(uService.getAllUsers() , HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable long id) {
		return new ResponseEntity<Users>(uService.getUserById(id) , HttpStatus.OK);
		
	}
	
	@PostMapping("/user/{uId}/Products/{pId}")
	public ResponseEntity<Users> setProductsToUsers(@PathVariable long uId,@PathVariable long pId) {
		return new ResponseEntity<Users>(uService.setProductsToUsers(uId, pId) ,  HttpStatus.OK);
	}

}



