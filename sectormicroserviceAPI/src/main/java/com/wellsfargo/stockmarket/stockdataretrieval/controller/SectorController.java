package com.wellsfargo.stockmarket.stockdataretrieval.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.stockmarket.stockdataretrieval.model.Company;
import com.wellsfargo.stockmarket.stockdataretrieval.model.Sector;
import com.wellsfargo.stockmarket.stockdataretrieval.service.SectorService;

@RestController
public class SectorController {
	
	@Autowired
	private SectorService sectorService;
	
	// This get request is to return the list of all the sectors 
	@RequestMapping("/sectors")
	public List<Sector> getAllSectors(){
		return sectorService.getAllSectors();
	}
	
	// This get request is to return the sector based on the sector id
	@GetMapping("/sectors/{sectorid}")
	public Sector getSector(@PathVariable Long sectorid){
		return sectorService.getSector(sectorid);
	}
	
	// This get request returns the list of all the companies in a particular sector
	@GetMapping("/sectors/{sectorid}/companies")
	public List<Company> getCompany(@PathVariable Long sectorid){
		return sectorService.getCompany(sectorid);
	}
	
	//This get request returns the average price of all the stocks in a sector
	@GetMapping("/sectors/{sectorid}/price")
	public String getPrice(@PathVariable Long sectorid){
		return sectorService.getPrice(sectorid);
	}
	
	// This get request returns the average price of all the stocks in a sector within a given date 
	@GetMapping("/sectors/{sectorid}/price/{from}/{to}")
	public String getFromToPrice(@PathVariable Long sectorid, @PathVariable String from, @PathVariable String to){
		return sectorService.getFromToPrice(sectorid, from, to);
	}
	
	// This get request returns the average price of all the stocks in a sector for the last period days mentioned
	@GetMapping("/sectors/{sectorid}/price/{period}")
	public String getPeriodPrice(@PathVariable Long sectorid, @PathVariable Integer period){
		return sectorService.getPeriodPrice(sectorid,period);
	}
	
	
	// This post request adds a new sector
	@PostMapping("/sectors")
	public void addSector(@RequestBody Sector sector) {
		sectorService.addSector(sector);
	}
	
	// This delete request deletes a sector by its sector id
	@DeleteMapping("/sectors/{sectorid}")
	public String deleteSector(@PathVariable Long sectorid) {
		return sectorService.deleteSector(sectorid);
	}
}
