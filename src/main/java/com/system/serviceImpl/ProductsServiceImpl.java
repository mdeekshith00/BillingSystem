package com.system.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.system.dto.ProductsDto;
import com.system.exception.ProductNotFoundException;
import com.system.model.Admin;
import com.system.model.Products;
import com.system.modeldto.ProductsModelDto;
import com.system.repositary.AdminRepositary;
import com.system.repositary.ProductsRepositary;
import com.system.service.ProductsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductsServiceImpl implements ProductsService {
	
	private final AdminRepositary pAdminRepositary;
    private final ProductsRepositary prodRepositary;
	
	private final ModelMapper modelMapper;
	
	
	@Override
	public ProductsDto addProducts(ProductsModelDto productsModelDto) {
		// TODO Auto-generated method stub
		
	   Products product =  modelMapper.map(productsModelDto, Products.class);
	   prodRepositary.save(product);
	   ProductsDto productdto = modelMapper.map(product, ProductsDto.class);
	   return productdto;
	}

	@Override
	 public List<ProductsDto> getAllProducts() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Admin> userDetails = pAdminRepositary.findByUserName(userName);

        if (userDetails.isEmpty()) {
            throw new UsernameNotFoundException("Admin not found: " + userName);
        }

        List<Products> allProducts = prodRepositary.findAll();

        List<ProductsDto> result = allProducts.stream()
                .filter(p -> p.getUserProducts() != null && !p.getUserProducts().isEmpty()
                        && p.getUserProducts().get(0).getUser().getAdmins().stream()
                        .anyMatch(admin -> admin.getUsername().equalsIgnoreCase(userName)))
                .map(p -> modelMapper.map(p, ProductsDto.class))
                .collect(Collectors.toList());

        return result;
    }

	@Override
	public ProductsDto getProdutsById(Integer productId) {
		// TODO Auto-generated method stub
	
		Products product = prodRepositary.findById(productId).orElseThrow(() -> 
		new ProductNotFoundException("Product Not Found on This Id:" + productId));
		
          return  modelMapper.map(product, ProductsDto.class);

	}

}


