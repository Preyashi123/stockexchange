package com.wellsfargo.stockmarket.stockdataretrieval;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SectorController {
	
	@Autowired
	private SectorService sectorService;
	
	// This get request is to return the list of all the sectors 
	@RequestMapping("/sectors")
	public List<Sector> getAllSectors(){
		List<Sector> sectors = sectorService.getAllSectors();
		return sectors;
	}
	
	
	// This fet request is to return the sector based on the sector id
	@GetMapping("/sectors/{sectorid}")
	public Sector getSector(@PathVariable Integer sectorid){
		Sector sector = sectorService.getSector(sectorid);
		return sector;
	}
	
	// This get request returns the list of all the companies in a particular sector
	@GetMapping("/sectors/{sectorid}/companies")
	public List<Company> getCompany(@PathVariable Integer sectorid){
		Sector sector = sectorService.getSector(sectorid);
		String name = sector.getSectorName();
		List<Company> companies = sectorService.getCompany(name);
		return companies;
	}
	
	//This get request returns the average price of all the stocks in a sector
	@GetMapping("/sectors/{sectorid}/price")
	public String getPrice(@PathVariable Integer sectorid){
		double price = 0.0;
		double averageSectorPrice = 0.0;
		Sector sector = sectorService.getSector(sectorid);
		String name = sector.getSectorName();
		List<Company> companies = sectorService.getCompany(name);
		for(Company c: companies) {
			String companyName = c.getCompanyName();
			List<Stock> stocks = sectorService.getStocks(companyName);
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
	
	// This get request returns the average price of all the stocks in a sector within a given date 
	@GetMapping("/sectors/{sectorid}/price/{from}/{to}")
	public String getFromToPrice(@PathVariable Integer sectorid, @PathVariable String from, @PathVariable String to){
		double price = 0.0;
		double averageSectorPriceFromTo = 0.0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate fromDate = LocalDate.parse(from,dtf);
		LocalDate toDate = LocalDate.parse(to,dtf);
		Sector sector = sectorService.getSector(sectorid);
		String name = sector.getSectorName();
		List<Company> companies = sectorService.getCompany(name);
		for(Company c: companies) {
			String companyName = c.getCompanyName();
			List<Stock> stocks = sectorService.getStocks(companyName);
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
	
	// This get request returns the average price of all the stocks in a sector for the last period days mentioned
	@GetMapping("/sectors/{sectorid}/price/{period}")
	public String getFromToPrice(@PathVariable Integer sectorid, @PathVariable Integer period){
		double price = 0.0;
		double averageSectorPricePeriod = 0.0;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String currentDate = "30/08/2020";
		LocalDate currentLocalDate = LocalDate.parse(currentDate,dtf);
		LocalDate fromDate = currentLocalDate.minusDays(period);
		Sector sector = sectorService.getSector(sectorid);
		String name = sector.getSectorName();
		List<Company> companies = sectorService.getCompany(name);
		for(Company c: companies) {
			String companyName = c.getCompanyName();
			List<Stock> stocks = sectorService.getStocks(companyName);
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
	
	
	// This post request adds a new sector
	@PostMapping("/sectors")
	public void addSector(@RequestBody Sector sector) {
		sectorService.addSector(sector);
	}
	
	// This delete request deletes a sector by its sector id
	@DeleteMapping("/sectors/{sectorid}")
	public String deleteSector(@PathVariable Integer sectorid) {
		
		Sector sector = sectorService.getSector(sectorid);
		if(sector != null) {
			sectorService.deleteSector(sectorid);
			return "Sector deleted";
		}
		return "Sector doesn't exist";	
	}
}
