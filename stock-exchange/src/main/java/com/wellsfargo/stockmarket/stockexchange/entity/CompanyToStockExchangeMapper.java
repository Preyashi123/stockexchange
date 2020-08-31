package com.wellsfargo.stockmarket.stockexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company_x_stockexchange", schema = "stockexchange")
public class CompanyToStockExchangeMapper {

	@Id
	@Column(name = "stockcode")
	private String stockCode;

	@Column(name = "companycode")
	private Integer companyCode;

	@Column(name = "stockexchange")
	private String stockExchange;

	// Default Constructor
	public CompanyToStockExchangeMapper() {

	}

	// Parameterized Constructor
	public CompanyToStockExchangeMapper(String stockCode, Integer companyCode, String stockExchange) {
		super();
		this.stockCode = stockCode;
		this.companyCode = companyCode;
		this.stockExchange = stockExchange;
	}

	// Getters and Setters

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public Integer getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Integer companyCode) {
		this.companyCode = companyCode;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

}
