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
import com.wellsfargo.stockmarket.stockdataretrieval.model.SectorPriceModel;
import com.wellsfargo.stockmarket.stockdataretrieval.service.SectorService;

@RestController
public class SectorController {
	
	@Autowired
	private SectorService sectorService;
	
	@Autowired
	private SectorPriceModel sectorPriceModel;
	
	// This get request is to return the list of all the sectors 
	@RequestMapping("/sectors")
	public List<Sector> getAllSectors(){
		return sectorService.getAllSectors();
	}
	
	// This get request is to return the sector based on the sector id
	@RequestMapping("/sectors/{sectorid}")
	public Sector getSector(@PathVariable Long sectorid){
		return sectorService.getSector(sectorid);
	}
	
	// This get request returns the list of all the companies in a particular sector
	@RequestMapping("/sectors/{sectorid}/companies")
	public List<Company> getCompany(@PathVariable Long sectorid){
		return sectorService.getCompany(sectorid);
	}
	
	//This get request returns the average price of all the stocks in a list of sectors
	@RequestMapping("/sectors/price")
	public List<String> getPrice(@RequestBody SectorPriceModel sectorPriceModel){
		return sectorService.getPrice(sectorPriceModel);
	}
	
	// This get request returns the average price of all the stocks in a list of sectors within a given date 
	@RequestMapping("/sectors/price/{from}/{to}")
	public List<String> getFromToPrice(@RequestBody SectorPriceModel sectorPriceModel){
		return sectorService.getFromToPrice(sectorPriceModel);
	}
	
	// This get request returns the average price of all the stocks in a list of sectors for the last period days mentioned
	@RequestMapping("/sectors/price/{period}")
	public List<String> getPeriodPrice(@RequestBody SectorPriceModel sectorPriceModel){
		return sectorService.getPeriodPrice(sectorPriceModel);
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
