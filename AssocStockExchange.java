package com.wellsfargo.stockmarket.companydetails.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AssocStockExchange {

	@Column(name="stockexchange", nullable = false, unique = true)
	private String stockexchange;
	
	@Column(name="stockcode", nullable = false, unique = true)
	private String stockcode;

	public String getStockexchange() {
		return stockexchange;
	}

	public void setStockexchange(String stockexchange) {
		this.stockexchange = stockexchange;
	}

	public String getStockcode() {
		return stockcode;
	}

	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
	
	
	
	
	
}
