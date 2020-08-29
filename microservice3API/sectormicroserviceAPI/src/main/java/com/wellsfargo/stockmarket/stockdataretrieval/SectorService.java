package com.wellsfargo.stockmarket.stockdataretrieval;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Sector getSector(Integer sectorid) {
		return sectorRepo.getOne(sectorid);
	}

	public void addSector(Sector sector) {
		sectorRepo.save(sector);
	}

	public void deleteSector(Integer sectorid) {
		sectorRepo.deleteById(sectorid);
	}	
	
	public List<Company> getCompany(String name) {
		return companyRepo.findBySectorName(name);
	}

	public List<Stock> getStocks(String companyName) {
		return stockRepo.findByCompanyName(companyName);
	}
	
}
