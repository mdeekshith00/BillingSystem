package com.system.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.exception.ReourceNotFoundException;
import com.system.model.Products;
import com.system.model.UserProducts;
import com.system.model.Users;
import com.system.repositary.ProductsRepositary;
import com.system.repositary.UserProductsRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.UserProductsService;

@Service
public class UserProductsServiceImpl implements UserProductsService{
	@Autowired
	private UsersRepositary userupRepositary;
	@Autowired
	private ProductsRepositary productupRepositary;
	@Autowired
	private UserProductsRepositary  userProductsRepositary ;
	
	
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
public List<UserProducts> getAllUserProducts() {
	// TODO Auto-generated method stub
	return userProductsRepositary.findAll();
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
//POST http://localhost:8080/user-products/assign?userId=1&productId=5&quantity=3
