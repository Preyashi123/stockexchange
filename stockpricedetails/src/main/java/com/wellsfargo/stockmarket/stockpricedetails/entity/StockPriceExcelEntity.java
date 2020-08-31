package com.wellsfargo.stockmarket.stockpricedetails.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companystockprice")

public class StockPriceExcelEntity {

	@Column(name = "companystockpriceid")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long companyStockPriceId;

	@Column(updatable = true, name = "companycode", length = 255)
	private double companyCode;

	@Column(name = "stockexchange", length = 255)
	private String stockExchange;

	@Column(name = "currentprice")
	private double currentPrice;

	@Column(name = "stockpricedate")
	private Date stockPriceDate;

	@Column(name = "stockpricetime", length = 255)
	private String stockPriceTime;

	public StockPriceExcelEntity() {

	}

	public double getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(double companyCode) {
		this.companyCode = companyCode;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Date getStockPriceDate() {
		return stockPriceDate;
	}

	public void setStockPriceDate(Date stockPriceDate) {
		this.stockPriceDate = stockPriceDate;
	}

	public String getStockPriceTime() {
		return stockPriceTime;
	}

	public void setStockPriceTime(String stockPriceTime) {
		this.stockPriceTime = stockPriceTime;
	}

}
