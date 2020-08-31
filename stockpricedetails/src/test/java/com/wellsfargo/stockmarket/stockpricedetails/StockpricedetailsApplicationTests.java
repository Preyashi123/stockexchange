package com.wellsfargo.stockmarket.stockpricedetails;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;


import java.util.List;
import org.hamcrest.Matchers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wellsfargo.stockmarket.stockpricedetails.entity.StockPriceExcelEntity;
import com.wellsfargo.stockmarket.stockpricedetails.service.StockPriceExcelService;

@SpringBootTest
class StockpricedetailsServiceTests {

	private static final String MESSAGE_ONE = "StockPriceExcelService does not load file properly into database";
	private static final String MESSAGE_TWO = "StockPriceExcelService does not handle null values";
	@Test
	void contextLoads() {
	}

	@Autowired
	private StockPriceExcelService stockpriceexcelservice;
	@Test
	public void CheckServiceFileUploading() throws Throwable {
		
 		List<StockPriceExcelEntity> value;
			value = (List<StockPriceExcelEntity>)stockpriceexcelservice.save(null);
			assertThat(MESSAGE_ONE,value,Matchers.hasSize(9));
		
		}
	@Test
	public void CheckServiceFileUploadingwithValueNull() throws Throwable {
		
 		List<StockPriceExcelEntity> value;
			value = (List<StockPriceExcelEntity>)stockpriceexcelservice.save(null);
		
		}
 		
}
	
	

