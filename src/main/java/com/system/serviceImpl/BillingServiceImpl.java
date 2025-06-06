package com.system.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dto.BillingDto;
import com.system.exception.ReourceNotFoundException;
import com.system.model.Billing;
import com.system.model.Users;
import com.system.modeldto.BillingModelDto;
import com.system.repositary.BillingRepositary;
import com.system.repositary.UsersRepositary;
import com.system.service.BillingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BillingServiceImpl  implements BillingService {
	
	
	private  final BillingRepositary billingRepositary;
	
	private final ModelMapper modelMapper;
	
	
	@Override
	public BillingDto addBilling(BillingModelDto billingModelDtoling) {
		// TODO Auto-generated method stub
		
		Billing billing = modelMapper.map(billingModelDtoling, Billing.class);
		billingRepositary.save(billing);
				
		BillingDto billingDto = modelMapper.map(billing, BillingDto.class);
		return billingDto;
		
	}
		
	@Override
	public BillingDto getBillById(Integer id) {
		// TODO Auto-generated method stub
		Billing billing =  billingRepositary.findById(id).orElseThrow(() ->
		new ReourceNotFoundException("Billing Id Not Found On this" + id));
		
		BillingDto bDto = modelMapper.map(billing, BillingDto.class);

		return bDto;
	
	}
	@Override
	public List<BillingDto> getAllBill() {
		// TODO Auto-generated method stub
		List<Billing> billList =  billingRepositary.findAll();
		List<BillingDto> billDtoList = billList.stream()
				.map(a ->modelMapper.map(billList, BillingDto.class)).toList();
		return billDtoList;
	}
}


