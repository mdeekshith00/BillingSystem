package com.system.serviceImpl;

import java.time.LocalDate;
import java.time.YearMonth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.system.repositary.BillingRepositary;
import com.system.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SchedulerServiceImpl implements ScheduleService {
		
	
	private final  BillingRepositary sBillingRepositary;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	
	@Scheduled(cron = "0 0 1 * * ?")  // the task will run at midnight on the first day of every month , zone = "Indian"
	public void processDeleteAllBilling() {
		LocalDate today = LocalDate.now();
		YearMonth yearMonth = YearMonth.from(today);
		LocalDate lastDay = yearMonth.atEndOfMonth();
		
		if(today.equals(lastDay)) { 
//			  System.out.println("Last day of month detected. Deleting data..."); 
			  sBillingRepositary.deleteAll();
			  LOGGER.info("Last day of month detected. Deleting data...");
			 
		}
		 else {
	            System.out.println("Today is not the last day. Skipping deletion.");
	        }
		
	}
	

}
