package com.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.system.model.Admin;
import com.system.serviceImpl.AdminServiceImpl;

@RestController
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/register")
    public ResponseEntity<Admin> register(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.register(admin), HttpStatus.CREATED);
    }

    @GetMapping("/users/{aId}")
    public ResponseEntity<Admin> getById(@PathVariable Integer aId) {
        return new ResponseEntity<>(adminService.getById(aId), HttpStatus.OK);
    }

    @GetMapping("/login")
    public String login() {
        return "login Successfully";
    }

    @PostMapping("/signup")
    public ResponseEntity<Admin> signUp(@RequestBody Admin admin) {
        return new ResponseEntity<>(adminService.signUp(admin), HttpStatus.CREATED);
    }
}

	


