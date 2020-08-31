package com.wellsfargo.stockmarket.stockexchange.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.stockmarket.stockexchange.entity.Company;
import com.wellsfargo.stockmarket.stockexchange.entity.CompanyToStockExchangeMapper;
import com.wellsfargo.stockmarket.stockexchange.entity.StockExchange;
import com.wellsfargo.stockmarket.stockexchange.repository.CompanyRepository;
import com.wellsfargo.stockmarket.stockexchange.repository.CompanyToStockExchangeMapperRepository;
import com.wellsfargo.stockmarket.stockexchange.repository.StockExchangeRepository;

@Service
public class StockExchangeService {

	@Autowired
	private StockExchangeRepository stockExchangeRepository;

	@Autowired
	private CompanyToStockExchangeMapperRepository companyToStockExchangeMapperRepository;

	@Autowired
	private CompanyRepository companyRepository;

	// Returns all the stock exchanges present in database
	public List<StockExchange> getStockExchangeList() {
		return stockExchangeRepository.findAll();
	}

	// Returns stock exchange with the specific id
	public StockExchange getStockExchange(Integer id) {
		return stockExchangeRepository.findById(id).get();
	}

	// Adds a Stock Exchange to our repository
	public void addStockExchange(StockExchange stockExchange) {
		stockExchangeRepository.save(stockExchange);
	}

	// Deletes the Stock Exchange from our repository
	public void deleteStockExchange(Integer id) {
		stockExchangeRepository.deleteById(id);
	}

	//Returns all the company objects listed in a stock exchange
	public List<Company> getAllCompaniesList(Integer id) {

		StockExchange stockExchange = new StockExchange();
		stockExchange = stockExchangeRepository.findById(id).get();
		List<CompanyToStockExchangeMapper> companyToStockExchangeMapperList = new ArrayList<>();
		companyToStockExchangeMapperList = companyToStockExchangeMapperRepository
				.findAllByStockExchange(stockExchange.getStockExchange());
		List<Company> companyList = new ArrayList<>();
		Company company;
		for (int i = 0; i < companyToStockExchangeMapperList.size(); i++) {
			Optional<Company> c = companyRepository.findByCompanyCode(companyToStockExchangeMapperList.get(i).getCompanyCode());
			company = c.get();
			companyList.add(company);
		}
		
		return companyList;
	}

}
