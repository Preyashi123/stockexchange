package com.wellsfargo.stockmarket.stockpricedetails;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;

import java.sql.Date;
//import java.util.Date;
import java.util.List;
import org.hamcrest.Matchers;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import static org.junit.Assert.*;

import com.wellsfargo.stockmarket.stockpricedetails.entity.StockPriceExcelEntity;
import com.wellsfargo.stockmarket.stockpricedetails.service.ServiceHelperReadExcel;
import com.wellsfargo.stockmarket.stockpricedetails.service.StockPriceExcelService;

 
@SpringBootTest
class StockpricedetailsServiceTests {

	private static final String MESSAGE_ONE = "StockPriceExcelService does not load file properly into database";
	@Test
	void contextLoads() {
	}

	@Autowired
	private StockPriceExcelService stockpriceexcelservice;
	
	@Test
	public void CheckServiceFileUploading() {
		
 		List<StockPriceExcelEntity> value;
			value = (List<StockPriceExcelEntity>)stockpriceexcelservice.save();
			assertThat(MESSAGE_ONE,value,Matchers.hasSize(9));
		
		}
		
	@Test
	void matchesInput() {
		StockPriceExcelEntity stock = new StockPriceExcelEntity();
		stock.setCompanyCode(500112);
		stock.setStockExchange("BSE");
		stock.setStockPriceTime("10:30:00");
		stock.setCurrentPrice(356.23);
		stock.setStockPriceDate(null);
		StockPriceExcelEntity stock1 = ServiceHelperReadExcel.readCellValues().get(0);
		assertEquals(stock.getCompanyCode(), stock1.getCompanyCode(), "company code not correct");
		assertEquals(stock.getStockExchange(), stock1.getStockExchange(), "Exchange name not correct");
		assertEquals(stock.getCurrentPrice(), stock1.getCurrentPrice(), "current price name not correct");
		
	}
	
	
 		
}
	
	

