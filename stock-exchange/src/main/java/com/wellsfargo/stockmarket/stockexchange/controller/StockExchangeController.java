package com.wellsfargo.stockmarket.stockexchange.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.stockmarket.stockexchange.entity.Company;
import com.wellsfargo.stockmarket.stockexchange.entity.StockExchange;
import com.wellsfargo.stockmarket.stockexchange.service.StockExchangeService;

/*Rest controller annotation enables the handler methods in this controller to be exposed as 
RESTful APIs */
@RestController
public class StockExchangeController {

	// Dependency Injection
	@Autowired
	private StockExchangeService stockExchangeService;

	// Returns all stock exchanges in the database
	@GetMapping("/stockexchanges")
	public List<StockExchange> getAllStockExchanges() {
		return stockExchangeService.getStockExchangeList();
	}

	//Returns stock exchange from database with specific id
	@GetMapping("/stockexchanges/{id}")
	public ResponseEntity<StockExchange> getStockExchange(@PathVariable Integer id) {
		try {
			StockExchange stockExchange = stockExchangeService.getStockExchange(id);
			return new ResponseEntity<StockExchange>(stockExchange, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<StockExchange>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Adds a stockExchange in our database
	@PostMapping("/stockexchanges")
	public void addStockExchange(@RequestBody StockExchange stockExchange) {
		stockExchangeService.addStockExchange(stockExchange);
	}
	
	//Deletes the stock exchange from our database
	@DeleteMapping("/stockexchanges/{id}")
	public void deleteStockExchange(@PathVariable Integer id) {
		stockExchangeService.deleteStockExchange(id);
	}
	
	//
	@GetMapping("/stockexchanges/{id}/companies")
	public List<Company> getAllCompaniesList(@PathVariable Integer id) {
		return stockExchangeService.getAllCompaniesList(id);
	}
		
}
