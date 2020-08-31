package com.wellsfargo.stockmarket.stockexchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.stockmarket.stockexchange.entity.StockExchange;

public interface StockExchangeRepository extends JpaRepository<StockExchange, Integer> {
	
	public StockExchange findByStockExchange(String stockExchange);

}
