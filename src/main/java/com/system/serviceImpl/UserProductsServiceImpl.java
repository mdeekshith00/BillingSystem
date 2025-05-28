package com.system.serviceImpl;

public class UserProductsServiceImpl {

}
//package com.system.controller;
//
//import com.system.model.Products;
//import com.system.model.UserProducts;
//import com.system.model.Users;
//import com.system.repository.ProductRepository;
//import com.system.repository.UserProductsRepository;
//import com.system.repository.UsersRepository;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
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
