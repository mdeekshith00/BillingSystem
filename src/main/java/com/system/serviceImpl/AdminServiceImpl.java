package com.system.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.dto.AdminDto;
import com.system.dto.BillingDto;
import com.system.dto.UsersDto2;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Admin;
import com.system.model.Billing;
import com.system.model.BillingItem;
import com.system.model.Products;
import com.system.model.UserProducts;
import com.system.model.Users;
import com.system.modeldto.AdminModelDto;
import com.system.repositary.AdminRepositary;
import com.system.repositary.BillingItemRepositary;
import com.system.repositary.BillingRepositary;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UserProductsRepositary;
import com.system.service.AdminService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements  UserDetailsService , AdminService   {
	
	
	private final AdminRepositary adminRepositary;
	
	private final ProductsRepositary aProductRepositary;
	
	private final UserProductsRepositary aUserProductsRepositary;
	
	private final BillingRepositary aBillingRepositary;
	
	private final BillingItemRepositary aBillingItemRepositary;
	
	private final JWTService jwtservice;
	@Autowired
	 AuthenticationManager authManager;
	
	private final ModelMapper modelMapper;
	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	    Admin admin = adminRepositary.findByUserName(userName)
	        .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + userName));

	    return new org.springframework.security.core.userdetails.User(
	        admin.getUsername(),
	        admin.getPassword(),
	        admin.getAuthorities()  
	    );
	}

	@Override	
	public AdminDto register(AdminModelDto adminModelDto) {
		// TODO Auto-generated method stub
		
		Admin admin =new Admin();
		
		admin.setUserName(adminModelDto.getUserName());
		admin.setPassword(bCryptPasswordEncoder.encode(adminModelDto.getPassword()));
		admin.setRole(adminModelDto.getRole());
		admin=adminRepositary.save(admin);
		
		return modelMapper.map(admin, AdminDto.class) ;
	}

	@Override
	public AdminDto signUp(AdminModelDto adminModelDto) {
		// TODO Auto-generated method stub

		Admin admin = new Admin();
		admin.setUserName(adminModelDto.getUserName());
		admin.setPassword(bCryptPasswordEncoder.encode(adminModelDto.getPassword()));
		admin.setRole(adminModelDto.getRole());
		
		admin=adminRepositary.save(admin);
		
		return  modelMapper.map(admin, AdminDto.class);
	}

	@Override
	public AdminDto getById(Integer aId) {
		// TODO Auto-generated method stub
		
		Admin admin =  adminRepositary.findById(aId).orElseThrow(() -> 
		new ReourceNotFoundException("Admin Id Not Found On This AdminId:" + aId));
		
		return modelMapper.map(admin, AdminDto.class);
		
	}

	@Override	
	public String verify(AdminModelDto adminModelDto) {
		// TODO Auto-generated method stub
		Admin admin = modelMapper.map(adminModelDto, Admin.class);
		
		Authentication authentication  = 
		authManager.authenticate(new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword()));
		
         if(authentication.isAuthenticated()) {
         return jwtservice.generateToken(admin.getUsername());
         }
         return "fail";
	}
	 
	    public String forgotPassword(String userName , Integer aId) {
	    	Optional<Admin> username = adminRepositary.findByUserName(userName);
	    	Optional<Admin> userId = adminRepositary.findById(aId);
	    	
	    	if(username.isEmpty()  && userId.get().getAId() != aId && userId.isEmpty()) {
	    		throw new UsernameNotFoundException("User not found On thid data :'" +  userName + "' Id " + aId);
	    	}
	    	Admin admin = new Admin();
	    	admin = username.get();
	    
	    	String token = UUID.randomUUID().toString();
	    	if(admin.getAId().equals(userId.get().getAId())) {
	    	admin.setResetToken(token);
	    	adminRepositary.save(admin);
	    	} else {
	    		throw new ReourceNotFoundException("Wrong User Id Provided : " + " Id '" + aId + "' please check again:"); 
	    	}
	    	
	    	return "Reset link genegerated. Use Token : " + token;
	    }
	    
	    public String resetPassword(String token , String newPassword ) {
	    	Optional<Admin> reset = adminRepositary.findByResetToken(token);
	    	if(reset.isEmpty()) {
	    		throw new RuntimeException("Token is inValid:");
	    	}
	    	Admin admin = reset.get();
	    	admin.setPassword(bCryptPasswordEncoder.encode(newPassword));
	    	admin.setResetToken(null);
	    	adminRepositary.save(admin);
	    	
	    	return "Password reset successful  on :" +  admin.getUsername();
	    }


		@Override	  
       public List<BillingDto> getAllBillsByProduct(String productName, String productCompany) {

	        List<BillingDto> billingDtoList = new ArrayList<>();

	        boolean hasProductName = productName != null && !productName.isEmpty();
	        boolean hasProductCompany = productCompany != null && !productCompany.isEmpty();
	        
	       
	       Products productname  =  aProductRepositary.findByProductName(productName);
	        List<UserProducts> userProductsByName = aUserProductsRepositary.findByProduct(productname);
	        List<BillingItem> billItems = aBillingItemRepositary.findByuserProductsIn(userProductsByName);
	        List<Billing> billing = aBillingRepositary.findByitemIdIn(billItems);
	   
	        Products productcompany = aProductRepositary.findByProductCompany(productCompany);
	        List<UserProducts> userProductsByCompany = aUserProductsRepositary.findByProduct(productcompany);
	        List<BillingItem> billItems1 = aBillingItemRepositary.findByuserProductsIn(userProductsByCompany);
	        List<Billing> billing1 = aBillingRepositary.findByitemIdIn(billItems1);
	      
	       
 
           List<Billing> bilList = aBillingRepositary.findAll();
           List<Products> productList = new ArrayList<Products>();
   
           if(!hasProductName && !hasProductCompany)  {
	       for(Billing b : bilList) {
	    	   BillingDto billDto = new BillingDto();
	    	   
	    	   Users user = b.getUser();
	    	   // user  to user Dto2
	    	   UsersDto2 userDto = new UsersDto2();
	    	   userDto.setUId(user.getUId());
	    	   userDto.setUName(user.getUName());
	    	   userDto.setMobileNo(user.getMobileNo());
	    	   userDto.setEMail(user.getEMail());
	    	   billDto.setUser(userDto);
	    	   billDto.setBId(b.getBId());
	 
	    	   List<UserProducts> userProducts  = user.getUserProducts();
	    	   for(UserProducts uerprod : userProducts) {
	    		   billDto.setProductName(uerprod.getProduct().getProductName()); 
	    		   billDto.setProductCompany(uerprod.getProduct().getProductCompany());
	    		   break;
	    	   }
	    	   
	    
	    		   billingDtoList.add(billDto);
		       }

	       }
 
           
	      if(hasProductName && hasProductCompany) {
	    	if(productname.getProductName().equalsIgnoreCase(productName) && productcompany.getProductCompany().equalsIgnoreCase(productCompany) ) {
	    		if(productname.getProductId() == productcompany.getProductId()) {
	    		for(Billing b : billing) {	
	    			Users user = b.getUser();
	    			UsersDto2 userDto = new UsersDto2();
	    			userDto.setUName(user.getUName());
	    			userDto.setUId(user.getUId());
	    			userDto.setEMail(user.getEMail());
	    			userDto.setMobileNo(user.getMobileNo());
	    			
	    			BillingDto billingDto = new BillingDto();
	    			billingDto.setUser(userDto);
	    			billingDto.setBId(b.getBId());
	    			billingDto.setProductName(productname.getProductName());
	    			billingDto.setProductCompany(productcompany.getProductCompany());
	    			
	    			billingDtoList.add(billingDto);
	    		}
	    			
	    		} else if(productname.getProductId() != productcompany.getProductId()) {
	    			billingDtoList.isEmpty();
	    		} 
	    		
	    		
	    	  } 
	    	
	    	
	      }
	      else  if(hasProductName) {
	    	  if(productname.getProductName().equalsIgnoreCase(productName)) {
		    		for(Billing b : billing) {
		    			Users user = b.getUser();
		    			UsersDto2 userDto = new UsersDto2();
		    			userDto.setUName(user.getUName());
		    			userDto.setUId(user.getUId());
		    			userDto.setEMail(user.getEMail());
		    			userDto.setMobileNo(user.getMobileNo());
		    			
		    			BillingDto billingDto = new BillingDto();
		    			billingDto.setUser(userDto);
		    			billingDto.setBId(b.getBId());
		    			billingDto.setProductName(productname.getProductName());
		    			billingDto.setProductCompany(productname.getProductCompany());
		    			
		    			billingDtoList.add(billingDto);
		    		}
		    		
		    		  
		    	  }
	    	  
	      }
	      else if(hasProductCompany) {
	    	  if(productcompany.getProductCompany().equalsIgnoreCase(productCompany)) {
		    		for(Billing b : billing1) {
		    			Users user = b.getUser();
		    			UsersDto2 userDto = new UsersDto2();
		    			userDto.setUName(user.getUName());
		    			userDto.setUId(user.getUId());
		    			userDto.setEMail(user.getEMail());
		    			userDto.setMobileNo(user.getMobileNo());
		    			
		    			BillingDto billingDto = new BillingDto();
		    			billingDto.setUser(userDto);
		    			billingDto.setBId(b.getBId());
		    			billingDto.setProductName(productcompany.getProductName());
		    			billingDto.setProductCompany(productcompany.getProductCompany());
		    			
		    			billingDtoList.add(billingDto);
		    		}
		    		
		    		  
		    	  }
	      }
	  
	       
	    
	        return billingDtoList;
    
		}
}
    
