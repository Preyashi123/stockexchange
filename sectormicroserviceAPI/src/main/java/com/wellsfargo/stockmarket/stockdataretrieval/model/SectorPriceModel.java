package com.wellsfargo.stockmarket.stockdataretrieval.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class SectorPriceModel {
	private List<Long> sectoridList;
	private String from;
	private String to;
	private Long period;
	
	public SectorPriceModel() {
		
	}
	
	public SectorPriceModel(List<Long> sectoridList, String from, String to, Long period) {
		super();
		this.sectoridList = sectoridList;
		this.from = from;
		this.to = to;
		this.period = period;
	}

	public List<Long> getSectoridList() {
		return sectoridList;
	}
	public void setSectoridList(List<Long> sectoridList) {
		this.sectoridList = sectoridList;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public Long getPeriod() {
		return period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}
	
	
}
