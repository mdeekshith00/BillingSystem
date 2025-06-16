package com.system.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.dto.AdminDto;
import com.system.dto.BillingItemDto;
import com.system.dto.UsersDto1;
import com.system.modeldto.AdminModelDto;
import com.system.serviceImpl.AdminServiceImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AdminController {

    private final  AdminServiceImpl adminService;       

    @PostMapping("/sign-up")
    public ResponseEntity<AdminDto> register(@Valid @RequestBody AdminModelDto adminModelDto) {
        return new ResponseEntity<>(adminService.register(adminModelDto), HttpStatus.CREATED);
    }
    
    @GetMapping("/sign-in")
    public ResponseEntity<?> login(@Valid @RequestBody AdminModelDto adminModelDto) {
    	 return new ResponseEntity<>(adminService.verify(adminModelDto), HttpStatus.CREATED);
    }
   
    @GetMapping("/users/{aId}")
    public ResponseEntity<AdminDto> getById(@PathVariable Integer aId) {
        return new ResponseEntity<>(adminService.getById(aId), HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String userName) {
        return new ResponseEntity<>(adminService.forgotPassword(userName), HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        return new ResponseEntity<>(adminService.resetPassword(token, newPassword), HttpStatus.OK);
    }

    @GetMapping("/admin/all-bills")
    public ResponseEntity<List<BillingItemDto>> getAllByproducts(@RequestParam(required = false) String productName,@RequestParam(required = false) String productCompany){
    	return new ResponseEntity<>(adminService.getAllBillsByProduct(productName, productCompany), HttpStatus.OK); 
    }
    
    @PostMapping("/admin/assign")
	public ResponseEntity<UsersDto1> setProductsToUsers(@Valid @RequestParam(required = false) Integer uId ,@RequestParam(required = false) Integer productId, @RequestParam(required = false) int quantity) {
		return new ResponseEntity<>(adminService.setProductsToUsers(uId, productId, quantity) , HttpStatus.OK);
	}
}