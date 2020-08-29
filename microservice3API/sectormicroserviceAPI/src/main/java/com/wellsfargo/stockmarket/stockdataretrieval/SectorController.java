package com.wellsfargo.stockmarket.stockdataretrieval;

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
	
	@RequestMapping("home")
	public String sayHi() {
		return "Hi";
	}
	
	@RequestMapping("/sectors")
	public List<Sector> getAllSectors(){
		List<Sector> sectors = sectorService.getAllSectors();
		return sectors;
	}
	
	@GetMapping("/sectors/{sectorid}")
	public String getSector(@PathVariable Integer sectorid){
		Sector sector = sectorService.getSector(sectorid);
		return sector.toString();
	}
	
	@GetMapping("/sectors/{sectorid}/companies")
	public List<Company> getCompany(@PathVariable Integer sectorid){
		Sector sector = sectorService.getSector(sectorid);
		String name = sector.getSectorName();
		List<Company> companies = sectorService.getCompany(name);
		return companies;
	}
	
	
	@GetMapping("/sectors/{sectorid}/price")
	public String getPrice(@PathVariable Integer sectorid){
		double price = 0.0;
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
		return "Total Sector Price is " + price;
	}
	
	@PostMapping("/sectors")
	public void addSector(@RequestBody Sector sector) {
		sectorService.addSector(sector);
	}
	
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
