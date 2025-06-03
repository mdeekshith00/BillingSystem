package com.system.modeldto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.system.model.Admin;
import com.system.model.BillingItem;
import com.system.model.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Component

public class BillingModelDto {
	
	private Integer bId;

	private Users user;

	private List<BillingItem> itemId;
	
	private Admin admin;

}
