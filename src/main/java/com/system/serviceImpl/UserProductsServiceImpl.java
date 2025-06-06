package com.system.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.UserProductsDto;
import com.system.dto.UsersDto1;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Products;
import com.system.model.UserProducts;
import com.system.model.Users;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UserProductsRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.UserProductsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserProductsServiceImpl implements UserProductsService{

	private final UsersRepositary userupRepositary;

	private final ProductsRepositary productupRepositary;

	private final UserProductsRepositary  userProductsRepositary ;
	
	private final ModelMapper modelMapper;
	
	
  public String assignProductToUser( Integer userId,  Integer productId,Integer quantity) {

      Users user = userupRepositary.findById(userId)
    		  .orElseThrow(() -> new ReourceNotFoundException("User Id Not Found On this UserID : " + userId));
      Products product = productupRepositary.findById(productId)
    		  .orElseThrow(() -> new ReourceNotFoundException("Product Id Not Found: " + productId));

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

	    userProductsRepositary.save(userProduct);

	    product.setQuantityAvailable(available - quantity);
	    productupRepositary.save(product);

	    UsersDto1 dto = new UsersDto1();
	    dto.setUName(user.getUName());
	    dto.setMobileNo(user.getMobileNo());
	    dto.setEMail(user.getEMail());
//	    dto.setUserProducts(List.of(userProduct));

      return "Product assigned to user successfully.";
  }


@Override
public List<UserProductsDto> getAllUserProducts() {
	// TODO Auto-generated method stub
	List<UserProducts> productList =  userProductsRepositary.findAll();
	List<UserProductsDto> productDaoList = productList.stream().
			map( a -> modelMapper.map(productList, UserProductsDto.class)).toList();
	return productDaoList;
}

}


