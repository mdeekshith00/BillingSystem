package com.system.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.UserProductsDto;
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

//      Integer check = product.setQuantityAvailable(product.getQuantityAvailable()-quantity);
      if (user == null && product == null) {
          return "Invalid user or product ID.";
      }
      
      

      UserProducts userProduct = new UserProducts();
      userProduct.setUser(user);
      userProduct.setProduct(product);
      userProduct.setMrp(product.getMRP());
      userProduct.setQuantity(quantity);
      
  
      productupRepositary.save(product);
      userProductsRepositary.save(userProduct);

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

//@RestController
//@RequestMapping("/user-products")
//public class UserProductController {
//
//    @Autowired
//    private UsersRepository usersRepository;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private UserProductsRepository userProductsRepository;
//
//    @PostMapping("/assign")
//    public String assignProductToUser(@RequestParam Integer userId,
//                                      @RequestParam Integer productId,
//                                      @RequestParam Integer quantity) {
//
//        Users user = usersRepository.findById(userId).orElse(null);
//        Products product = productRepository.findById(productId).orElse(null);
//
//        if (user == null || product == null) {
//            return "Invalid user or product ID.";
//        }
//
//        UserProducts userProduct = new UserProducts();
//        userProduct.setUser(user);
//        userProduct.setProduct(product);
//        userProduct.setQuantity(quantity);
//
//        userProductsRepository.save(userProduct);
//
//        return "Product assigned to user successfully.";
//    }
//}
