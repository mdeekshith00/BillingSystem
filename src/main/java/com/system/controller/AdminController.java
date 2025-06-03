package com.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.system.dto.AdminDto;
import com.system.model.Admin;
import com.system.modeldto.AdminModelDto;
import com.system.serviceImpl.AdminServiceImpl;

@RestController
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/sign-in")
    @PreAuthorize("Admin")
    public ResponseEntity<AdminDto> register(@RequestBody AdminModelDto adminModelDto) {
        return new ResponseEntity<>(adminService.register(adminModelDto), HttpStatus.CREATED);
    }

    @GetMapping("/users/{aId}")
    public ResponseEntity<AdminDto> getById(@PathVariable Integer aId) {
        return new ResponseEntity<>(adminService.getById(aId), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminModelDto adminModelDto) {
    	 return new ResponseEntity<>(adminService.verify(adminModelDto), HttpStatus.CREATED);
    }

    @PostMapping("/signup")
    public ResponseEntity<AdminDto> signUp(@RequestBody AdminModelDto adminModelDto) {
        return new ResponseEntity<>(adminService.signUp(adminModelDto), HttpStatus.CREATED);
    }
}

	

