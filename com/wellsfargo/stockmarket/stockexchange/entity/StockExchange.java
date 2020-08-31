package com.wellsfargo.stockmarket.stockexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_stockexchange", schema ="stockexchange")
public class StockExchange {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "stockexchangeid")
	private Integer stockExchangeId;
	
	@Column(name = "stockexchange")
	private String stockExchange;
	
	@Column(name = "brief")
	private String brief;
	
	@Column(name = "contactaddress")
	private String contactAddress;
	
	@Column(name = "remarks")
	private String remarks;
	
	//Default Constructor
	public StockExchange() {
		//do nothing
	}
	
	//Parameterized Constructor
	public StockExchange(Integer stockExchangeId, String stockExchange, String brief, String contactAddress, String remarks) {
		super();
		this.stockExchangeId = stockExchangeId;
		this.stockExchange = stockExchange;
		this.brief = brief;
		this.contactAddress = contactAddress;
		this.remarks = remarks;
	}
	public Integer getStockExchangeId() {
		return stockExchangeId;
	}

	public void setStockExchangeId(Integer stockExchangeId) {
		this.stockExchangeId = stockExchangeId;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
	


	