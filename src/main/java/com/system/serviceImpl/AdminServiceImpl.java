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
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.system.dto.AdminDto;
import com.system.dto.BillingItemDto;
import com.system.dto.UsersDto1;
import com.system.dto.UsersDto2;
import com.system.dto.UsersProductsDto1;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Admin;
import com.system.model.BillingItem;
import com.system.model.Products;
import com.system.model.UserProducts;
import com.system.model.Users;
import com.system.modeldto.AdminModelDto;
import com.system.repositary.AdminRepositary;
import com.system.repositary.BillingItemRepositary;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UserProductsRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.AdminService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements  UserDetailsService , AdminService   {
	
	
	private final AdminRepositary adminRepositary;
	
	private final ProductsRepositary aProductRepositary;
	
	private final UserProductsRepositary aUserProductsRepositary;
	
	private final UsersRepositary aUsersRepositary;
	
	private final BillingItemRepositary aBillingItemRepositary;
	
	private final JWTService jwtservice;
	@Autowired
	private  AuthenticationManager authManager;
	
	private final ModelMapper modelMapper;
	
	
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	    Admin admin = adminRepositary.findByUserName(userName)
	        .orElseThrow(() -> new UsernameNotFoundException("Admin not found with username: " + userName));

	    return new User(admin.getUsername(), 
	    		admin.getPassword(), List.of(new SimpleGrantedAuthority("Admin")));
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
	
	// login to get JWT token
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

	@Override
	public AdminDto getById(Integer aId) {
		// TODO Auto-generated method stub
		
		Admin admin =  adminRepositary.findById(aId).orElseThrow(() -> 
		new ReourceNotFoundException("Admin Id Not Found On This AdminId:" + aId));
		
		return modelMapper.map(admin, AdminDto.class);
		
	}

	    public String forgotPassword(String userName) {
	    	Optional<Admin> username = adminRepositary.findByUserName(userName);
	    	
	    	if(username.isEmpty() && username.get().getUsername() != userName) {
	    		throw new UsernameNotFoundException("User not found On this data :'" +  userName);
	    	} 
	    	Admin admin = new Admin();
	    	admin = username.get();
	    
	    	String token = UUID.randomUUID().toString();
	  
	    	admin.setResetToken(token);
	    	adminRepositary.save(admin); 

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
		public UsersDto1 setProductsToUsers(Integer uId, Integer productId, int quantity) {
	    	String str =   SecurityContextHolder.getContext().getAuthentication().getName();
	    	Optional<Admin> admin1 = adminRepositary.findByUserName(str);
	    	
		    Users user = aUsersRepositary.findById(uId)
		        .orElseThrow(() -> new ReourceNotFoundException("User Id Not Found: " + uId + " please add User then you can add Products to this users:"));

		    UsersDto1 dto = new UsersDto1();
		    
			List<Users> userAdmin = admin1.get().getUsers();
			
			for(Users u : userAdmin) {
				if(u.getUId() == user.getUId()) {
				
		    Products product = aProductRepositary.findById(productId)
		        .orElseThrow(() -> new ReourceNotFoundException("Product Id Not Found: " + productId ));

		    int available = product.getQuantityAvailable();

		    if (quantity > available) {
		        throw new ReourceNotFoundException("Product quantity is not available. Requested: " + quantity + ", Available: " + available);
		    }

		    if (user.getUserProducts() == null) {
		        user.setUserProducts(new ArrayList<>());
		    }

		    UserProducts userProduct = new UserProducts();
		    userProduct.setUser(user);
		    userProduct.setProduct(product);
		    userProduct.setMrp(product.getMRP());
		    userProduct.setQuantity(quantity);

		    user.getUserProducts().add(userProduct);
		    
		    aUserProductsRepositary.save(userProduct);

		    product.setQuantityAvailable(available - quantity);
		    aProductRepositary.save(product);
//	          ---------------------------------------------------------------------------   
		    UsersProductsDto1 updto = new UsersProductsDto1();
		    updto.setUserProductId(product.getProductId());
		    updto.setMrp(product.getMRP());
		    updto.setQuantity(product.getQuantityAvailable());
//		    updto.setProduct();
	
		    dto.setUId(user.getUId());
		    dto.setUName(user.getUName());
		    dto.setMobileNo(user.getMobileNo());
		    dto.setEMail(user.getEMail());
		    dto.setUserProducts(null);
			}
			}
		    return dto;
		}



		@Override	  
       public List<BillingItemDto> getAllBillsByProduct(String productName, String productCompany) {

	        List<BillingItemDto> billingDtoList = new ArrayList<>();

	        boolean hasProductName = productName != null && !productName.isEmpty();
	        boolean hasProductCompany = productCompany != null && !productCompany.isEmpty();

	       Products productname  =  aProductRepositary.findByProductName(productName);
	        List<UserProducts> userProductsByName = aUserProductsRepositary.findByProduct(productname);
	        List<BillingItem> billItems = aBillingItemRepositary.findByuserProductsIn(userProductsByName);

	        Products productcompany = aProductRepositary.findByProductCompany(productCompany);
	        List<UserProducts> userProductsByCompany = aUserProductsRepositary.findByProduct(productcompany);
	        List<BillingItem> billItems1 = aBillingItemRepositary.findByuserProductsIn(userProductsByCompany);
 
	        List<BillingItem> item = aBillingItemRepositary.findAll();
           List<Products> productList = new ArrayList<Products>();
   
           if(!hasProductName && !hasProductCompany)  {
	       for(BillingItem b : item) {
	    	   BillingItemDto billDto = new BillingItemDto();
	    	   
	    	   Users user = b.getUser();
	    	   // user  to user Dto2
	    	   UsersDto2 userDto = new UsersDto2();
	    	   userDto.setUId(user.getUId());
	    	   userDto.setUName(user.getUName());
	    	   userDto.setMobileNo(user.getMobileNo());
	    	   userDto.setEMail(user.getEMail());
//	    	   billDto.setUser(userDto);
//	    	   billDto.setBId(b.getBId());
	 
	    	   List<UserProducts> userProducts  = user.getUserProducts();
	    	   for(UserProducts uerprod : userProducts) {
	    		   billDto.setProductName(uerprod.getProduct().getProductName()); 
	    		   billDto.setProductComapny(uerprod.getProduct().getProductCompany());
	    		   break;
	    	   }
	    	   
	    
	    		   billingDtoList.add(billDto);
		       }

	       } 
	      if(hasProductName && hasProductCompany) {
	    	if(productname.getProductName().equalsIgnoreCase(productName) && productcompany.getProductCompany().equalsIgnoreCase(productCompany) ) {
	    		if(productname.getProductId() == productcompany.getProductId()) {
	    			 for(BillingItem b : item) {
	    		
	    			Users user = b.getUser();
	    			UsersDto2 userDto = new UsersDto2();
	    			userDto.setUName(user.getUName());
	    			userDto.setUId(user.getUId());
	    			userDto.setEMail(user.getEMail());
	    			userDto.setMobileNo(user.getMobileNo());
	    			
	    			  BillingItemDto billDto = new BillingItemDto();
	    			  billDto.setUser(userDto);
	    			  billDto.setProductName(productname.getProductName());
	    			  billDto.setProductComapny(productcompany.getProductCompany());
	    			
	    			billingDtoList.add(billDto);
	    		}
	    			
	    		} else if(productname.getProductId() != productcompany.getProductId()) {
	    			billingDtoList.isEmpty();
	    		} 
	    		
	    		
	    	  } 
	    	
	    	
	      }
	      else  if(hasProductName) {
	    	  if(productname.getProductName().equalsIgnoreCase(productName)) {
	    		  for(BillingItem b : item) {
		    			Users user = b.getUser();
		    			UsersDto2 userDto = new UsersDto2();
		    			userDto.setUName(user.getUName());
		    			userDto.setUId(user.getUId());
		    			userDto.setEMail(user.getEMail());
		    			userDto.setMobileNo(user.getMobileNo());
		    			
		    			  BillingItemDto billDto = new BillingItemDto();
		    			  billDto.setUser(userDto);
		    			  billDto.setProductName(productname.getProductName());
		    			  billDto.setProductComapny(productname.getProductCompany());
		    			
		    			billingDtoList.add(billDto);
		    		}
		    		
		    		  
		    	  }
	    	  
	      }
	      else if(hasProductCompany) {
	    	  if(productcompany.getProductCompany().equalsIgnoreCase(productCompany)) {
	    		  for(BillingItem b : item) {
		    			Users user = b.getUser();
		    			UsersDto2 userDto = new UsersDto2();
		    			userDto.setUName(user.getUName());
		    			userDto.setUId(user.getUId());
		    			userDto.setEMail(user.getEMail());
		    			userDto.setMobileNo(user.getMobileNo());
		    			
		    			 BillingItemDto billDto = new BillingItemDto();
		    			 billDto.setUser(userDto);
		    			 billDto.setProductName(productcompany.getProductName());
		    			 billDto.setProductComapny(productcompany.getProductCompany());
		    			
		    			billingDtoList.add(billDto);
		    		}
		    		  
		    	  }
	      }

	        return billingDtoList;
    
		}
}
    
