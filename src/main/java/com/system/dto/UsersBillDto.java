package com.system.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UsersBillDto {
    
	private Integer uId;
	
    private String uName;
    
    private String mobileNo;
    
    private String eMail;
    
    private BillingItemDtoMin BillingItems;

}
