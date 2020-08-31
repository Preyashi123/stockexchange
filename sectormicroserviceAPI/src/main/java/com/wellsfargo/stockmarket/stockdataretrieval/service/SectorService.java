package com.wellsfargo.stockmarket.stockdataretrieval.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.stockmarket.stockdataretrieval.repository.SectorRepo;
import com.wellsfargo.stockmarket.stockdataretrieval.model.Company;
import com.wellsfargo.stockmarket.stockdataretrieval.model.Sector;
import com.wellsfargo.stockmarket.stockdataretrieval.model.Stock;
import com.wellsfargo.stockmarket.stockdataretrieval.repository.CompanyRepo;
import com.wellsfargo.stockmarket.stockdataretrieval.repository.StockRepo;

@Service
public class SectorService {
	@Autowired
	private SectorRepo sectorRepo;
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private StockRepo stockRepo;
	
	public List<Sector> getAllSectors() {
		List<Sector> allSectors = sectorRepo.findAll();
		return allSectors;
	}

	public Sector getSector(Long sectorid) {
		return sectorRepo.findById(sectorid).orElse(null);
	}

	public void addSector(Sector sector) {
		sectorRepo.save(sector);
	}

	public String deleteSector(Long sectorid) {
		Sector sector = getSector(sectorid);
		if(sector != null) {
			sectorRepo.deleteById(sectorid);
			return "Sector deleted";
		}
		return "Sector doesn't exist";		
	}	
	
	public List<Stock> getStocks(String companyName) {
		return stockRepo.findByCompanyName(companyName);
	}

	public List<Company> getCompany(Long sectorid) {
		Sector sector = getSector(sectorid);
		return companyRepo.findBySectorName(sector.getSectorName());
	}
	
	public String getPrice(Long sectorid) {
		double price = 0.0;
		double averageSectorPrice = 0.0;
		List<Company> companies = getCompany(sectorid);
		for(Company c: companies) {
			String companyName = c.getCompanyName();
			List<Stock> stocks = getStocks(companyName);
			for(Stock s: stocks) {
				price = price + s.getPrice();
			}
		}
		averageSectorPrice = (double)price/companies.size();
		if(companies.size() == 0) {
			return "No Companies in this sector";
		}
		return "Average Sector Price is " + averageSectorPrice;
	}
	
	public String getFromToPrice(Long sectorid, String from, String to){
		double price = 0.0;
		double averageSectorPriceFromTo = 0.0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fromDate = LocalDate.parse(from,dtf);
		LocalDate toDate = LocalDate.parse(to,dtf);
		List<Company> companies = getCompany(sectorid);
		for(Company c: companies) {
			String companyName = c.getCompanyName();
			List<Stock> stocks = getStocks(companyName);
			for(Stock s: stocks) {
				LocalDate stockDate = LocalDate.parse(s.getDate(),dtf);
				if(stockDate.isAfter(fromDate) && stockDate.isBefore(toDate)) {
					price = price + s.getPrice();
				}
			}
		}
		if(companies.size() == 0) {
			return "No Companies in this sector";
		}
		averageSectorPriceFromTo = (double)price/companies.size();
		if(averageSectorPriceFromTo != 0.0)
			return "Average Sector Price from " + fromDate + " to " + toDate + " is " + averageSectorPriceFromTo;
		else
			return "No Stocks in the sector from " + fromDate + " to " + toDate;
	}

	public String getPeriodPrice(Long sectorid, Integer period) {
		double price = 0.0;
		double averageSectorPricePeriod = 0.0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String currentDate = "30/08/2020";
		LocalDate currentLocalDate = LocalDate.parse(currentDate,dtf);
		LocalDate fromDate = currentLocalDate.minusDays(period);
		List<Company> companies = getCompany(sectorid);
		for(Company c: companies) {
			String companyName = c.getCompanyName();
			List<Stock> stocks = getStocks(companyName);
			for(Stock s: stocks) {
				LocalDate stockDate = LocalDate.parse(s.getDate(),dtf);
				if(stockDate.isAfter(fromDate) && stockDate.isBefore(currentLocalDate)) {
					price = price + s.getPrice();
				}
			}
		}
		if(companies.size() == 0) {
			return "No Companies in this sector";
		}
		averageSectorPricePeriod = (double)price/companies.size();
		if(averageSectorPricePeriod != 0.0)
			return "Average Sector Price for a period of " + period + " days is " + averageSectorPricePeriod;
		else
			return "No stocks in the sector for last " + period + "days";
	}
	
}
