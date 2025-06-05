package com.system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.system.dto.AdminDto;
import com.system.modeldto.AdminModelDto;
import com.system.serviceImpl.AdminServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AdminController {

    
    private final  AdminServiceImpl adminService;
    

    @PostMapping("/sign-in")
    public ResponseEntity<AdminDto> register(@Valid @RequestBody AdminModelDto adminModelDto) {
        return new ResponseEntity<>(adminService.register(adminModelDto), HttpStatus.CREATED);
    }
   
    @GetMapping("/users/{aId}")
    public ResponseEntity<AdminDto> getById(@PathVariable Integer aId) {
        return new ResponseEntity<>(adminService.getById(aId), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AdminModelDto adminModelDto) {
    	 return new ResponseEntity<>(adminService.verify(adminModelDto), HttpStatus.CREATED);
    }

    @PostMapping("/signup")
    public ResponseEntity<AdminDto> signUp(@Valid @RequestBody AdminModelDto adminModelDto) {
        return new ResponseEntity<>(adminService.signUp(adminModelDto), HttpStatus.CREATED);
    }
}

	

