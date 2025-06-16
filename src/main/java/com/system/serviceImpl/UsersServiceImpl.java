package com.system.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.system.dto.AdminDto;
import com.system.dto.BillingItemDtoMin;
import com.system.dto.UserDtoMin;
import com.system.dto.UserProductsDto;
import com.system.dto.UsersBillDto;
import com.system.dto.UsersDto2;
import com.system.dto.UsersDtoMax;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Admin;
import com.system.model.BillingItem;
import com.system.model.Users;
import com.system.modeldto.UsersModelDto;
import com.system.repositary.AdminRepositary;
import com.system.repositary.BillingItemRepositary;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UserProductsRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.UsersService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {
	
	private final AdminRepositary uAdminRepositary;
	
	private final UsersRepositary userRepositary;
	
	private final ProductsRepositary pUserRepositary;
	
	private final BillingItemRepositary uBillingItemRepositary;
	
	private final UserProductsRepositary upUserRepositary;
	
	private final ModelMapper modelMapper;
	


	@SuppressWarnings("unchecked")
	@Override
	public UserDtoMin addUser(UsersModelDto userModelDto) {
	    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
	    Optional<Admin> userDetails = uAdminRepositary.findByUserName(userName);
	    
	    if (userDetails.isEmpty()) {
	        throw new ReourceNotFoundException("Admin not found for username: " + userName);
	    }

	    Admin admin1 = userDetails.get();
	  
	    Users user = new Users();
	    user.setUName(userModelDto.getUName());
	    user.setEMail(userModelDto.getEMail());
	    user.setMobileNo(userModelDto.getMobileNo()); 
	 
	    List<Users> userList = admin1.getUsers();

	    if (userList == null || userList.isEmpty()) {
	    	user.getAdmins().add(admin1);
	    	admin1.getUsers().add(user);
	        userRepositary.save(user);
	        uAdminRepositary.save(admin1);
	    } else {
	        boolean userExists = userList.stream()
	            .anyMatch(u -> u != null && u.getUName().equalsIgnoreCase(user.getUName()));

	        if (userExists) {
	            throw new ReourceNotFoundException(user.getUName() + " user details already exist.");
	        }else {
	        	user.getAdmins().add(admin1);
	        	admin1.getUsers().add(user);
	  	        userRepositary.save(user);
	  	      uAdminRepositary.save(admin1);
	        }
	    }

	   
	    return modelMapper.map(user, UserDtoMin.class);
	}

 
	@Override
	public UsersBillDto getBillByUserId(Integer uId , Integer itemId) {
		String userName  =  SecurityContextHolder.getContext().getAuthentication().getName();
		 Optional<Admin> userDetails = uAdminRepositary.findByUserName(userName);
	
		Users u =  userRepositary.findById(uId).orElseThrow(() -> 
		new ReourceNotFoundException("User Id Not Found On this UserID : " + uId));
		
		UsersBillDto userDto = new UsersBillDto();
		List<Users> userList = userDetails.get().getUsers();
		for(Users user :userList) {
			if(user.getUId() == u.getUId()) {
		
		
		BillingItem b = uBillingItemRepositary.findById(itemId).orElseThrow(() -> 
		new ReourceNotFoundException("Billing Id Not Found On this UserID : " + itemId));

		userDto.setUId(u.getUId());
		userDto.setUName(u.getUName());
		userDto.setMobileNo(u.getMobileNo());
		userDto.setEMail(u.getEMail());

		BillingItemDtoMin bill = new BillingItemDtoMin();
		bill.setItemId(b.getItemId());
        bill.setPrice(b.getPrice());
        bill.setQuantity(b.getQuantity());
        bill.setTotalAmount(b.getTotalAmount());
        
		userDto.setBillingItems(bill);
		}
		}
		return userDto;
		
	}

	@Override
	public List<UsersDto2> getAllUsers() {
	    String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();

	    Optional<Admin> adminOpt = uAdminRepositary.findByUserName(loggedInUsername);
	    if (!adminOpt.isPresent()) {
	        return new ArrayList<>();
	    }

	    Admin loggedInAdmin = adminOpt.get();
	    List<Users> allUsers = userRepositary.findAll();

	    List<UsersDto2> result = allUsers.stream()
	        .filter(user -> user.getAdmins() != null &&
	                        user.getAdmins().stream()
	                            .anyMatch(a -> a.getUsername().equalsIgnoreCase(loggedInAdmin.getUsername())))
	        .map(user -> {
	            UsersDto2 dto = new UsersDto2();
	            dto.setUId(user.getUId());
	            dto.setUName(user.getUName());
	            dto.setEMail(user.getEMail());
	            dto.setMobileNo(user.getMobileNo());
	            return dto;
	        })
	        .collect(Collectors.toList());

	    return result;
	}

	@Override
	public UsersDto2 getUserById(Integer uId) {
		// TODO Auto-generated method stub
		Users user =  userRepositary.findById(uId).orElseThrow(() -> 
		new ReourceNotFoundException("User Id Not Found On this UserID : " + uId));
		
		 UsersDto2 dto = new UsersDto2();
		    dto.setUId(user.getUId());
		    dto.setUName(user.getUName());
		    dto.setEMail(user.getEMail());
		    dto.setMobileNo(user.getMobileNo());
		    
		return dto;
	}

	@Override
	public UsersDtoMax getUserDetailsById(Integer uId) {
	    Users user = userRepositary.findById(uId)
	        .orElseThrow(() -> new ReourceNotFoundException("User ID Not Found: " + uId));

	    UsersDtoMax userDto = new UsersDtoMax();
	    userDto.setUId(user.getUId());
	    userDto.setUName(user.getUName());
	    userDto.setMobileNo(user.getMobileNo());
	    userDto.setEMail(user.getEMail());

	    List<Admin> adminList = user.getAdmins();
	    if (adminList != null && !adminList.isEmpty()) {
	        Admin admin = adminList.get(0);
	        AdminDto adminDto = new AdminDto();
	        adminDto.setUserName(admin.getUsername());
	        adminDto.setRole(admin.getRole());
	        userDto.setAdmin(adminDto);
	    }

	    List<UserProductsDto> productDtos = user.getUserProducts().stream()
	        .map(up -> {
	            UserProductsDto dto = new UserProductsDto();
	            dto.setUserProductId(up.getUserProductId());
	            dto.setMrp(up.getMrp());
	            dto.setQuantity(up.getQuantity());
	            return dto;
	        })
	        .collect(Collectors.toList());
	    
	    List<BillingItem> item  = user.getItemId();
	    List<BillingItemDtoMin> itemMin = item.stream().map(a -> modelMapper.map(a, BillingItemDtoMin.class)).toList();
	    userDto.setBillingItems(itemMin);

	    userDto.setUserProducts(productDtos);

	    return userDto;
	}


//	public UsersDto1 setProductsToUsers(Integer uId, Integer productId, int quantity) {
//	    Users user = userRepositary.findById(uId)
//	        .orElseThrow(() -> new ReourceNotFoundException("User Id Not Found: " + uId));
//
//	    Products product = pUserRepositary.findById(productId)
//	        .orElseThrow(()	 -> new ReourceNotFoundException("Product Id Not Found: " + productId));
//
//	    int available = product.getQuantityAvailable();
//
//	    if (quantity > available) {
//	        throw new ReourceNotFoundException("Product quantity is not available. Requested: " + quantity + ", Available: " + available);
//	    }
//
//
//	    if (user.getUserProducts() == null) {
//	        user.setUserProducts(new ArrayList<>());
//	    }
//
//	    UserProducts userProduct = new UserProducts();
//	    userProduct.setUser(user);
//	    userProduct.setProduct(product);
//	    userProduct.setMrp(product.getMRP());
//	    userProduct.setQuantity(quantity);
//
//	    user.getUserProducts().add(userProduct);
//
//	    upUserRepositary.save(userProduct);
//
//	    product.setQuantityAvailable(available - quantity);
//	    pUserRepositary.save(product);
////          ---------------------------------------------------------------------------
//	    UsersDto1 dto = new UsersDto1();
//	    	   
//	    UsersProductsDto1 updto = new UsersProductsDto1();
//	    updto.setUserProductId(product.getProductId());
//	    updto.setMrp(product.getMRP());
//	    updto.setQuantity(product.getQuantityAvailable());
////	    updto.setProduct();
//	    
//	    BillingDto1 bdto = new BillingDto1();
//	    
//	    
//	    dto.setUId(user.getUId());
//	    dto.setUName(user.getUName());
//	    dto.setMobileNo(user.getMobileNo());
//	    dto.setEMail(user.getEMail());
//	    dto.setUserProducts(List.of(updto));
//
//	    return dto;
//	}		
}