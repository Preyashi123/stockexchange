package com.wellsfargo.stockmarket.stockdataretrieval.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.stockmarket.stockdataretrieval.repository.SectorRepo;
import com.wellsfargo.stockmarket.stockdataretrieval.model.Company;
import com.wellsfargo.stockmarket.stockdataretrieval.model.Sector;
import com.wellsfargo.stockmarket.stockdataretrieval.model.SectorPriceModel;
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
	
	public List<String> getPrice(SectorPriceModel sectorPriceModel) {
		double price = 0.0;
		double averageSectorPrice = 0.0;
		List<String> res = null;
		List<Long> sectoridList = sectorPriceModel.getSectoridList();
		for(Long sectorid : sectoridList) {
			Sector sector = getSector(sectorid);
			String sectorName = sector.getSectorName();
			List<Company> companies = getCompany(sectorid);
			for(Company c: companies) {
				String companyName = c.getCompanyName();
				List<Stock> stocks = getStocks(companyName);
				for(Stock s: stocks) {
					price = price + s.getPrice();
				}
			}
		
			if(companies.size() == 0) {
				res.add("No Companies in this sector");
			}
			else {
				averageSectorPrice = (double)price/companies.size();
				if(averageSectorPrice != 0.0)
					res.add("Average Price for " + sectorName + " is " + averageSectorPrice);
				else
					res.add("No Stocks in the sector " + sectorName );
			}
		}
		return res;
	}
	
	public List<String> getFromToPrice(SectorPriceModel sectorPriceModel){
		double price = 0.0;
		double averageSectorPriceFromTo = 0.0;
		List<String> res = null;
		String from = sectorPriceModel.getFrom();
		String to = sectorPriceModel.getTo();
		List<Long> sectoridList = sectorPriceModel.getSectoridList();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fromDate = LocalDate.parse(from,dtf);
		LocalDate toDate = LocalDate.parse(to,dtf);
		for(Long sectorid: sectoridList) {
			Sector sector = getSector(sectorid);
			String sectorName = sector.getSectorName();
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
				res.add("No Companies in this sector");
			}
			else {
				averageSectorPriceFromTo = (double)price/companies.size();
				if(averageSectorPriceFromTo != 0.0)
					res.add("Average Price for " + sectorName + " from " + fromDate + " to " + toDate + " is " + averageSectorPriceFromTo);
				else
					res.add("No Stocks in the sector " + sectorName + " from " + fromDate + " to " + toDate);
			}
			
		}
		return res;
	}

	public List<String> getPeriodPrice(SectorPriceModel sectorPriceModel) {
		double price = 0.0;
		double averageSectorPricePeriod = 0.0;
		List<String> res = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String currentDate = "30/08/2020";
		Long period = sectorPriceModel.getPeriod();
		List<Long> sectoridList = sectorPriceModel.getSectoridList();
		LocalDate currentLocalDate = LocalDate.parse(currentDate,dtf);
		LocalDate fromDate = currentLocalDate.minusDays(period);
		for(Long sectorid: sectoridList) {
			Sector sector = getSector(sectorid);
			String sectorName = sector.getSectorName();
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
				res.add("No Companies in this sector");
			}
			averageSectorPricePeriod = (double)price/companies.size();
			if(averageSectorPricePeriod != 0.0)
				res.add("Average Sector Price for" + sectorName + " for a period of " + period + " days is " + averageSectorPricePeriod);
			else
				res.add("No stocks in the sector " + sectorName + " for last " + period + "days");
		}
		return res;
	}
	
}
