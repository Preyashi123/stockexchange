package com.wellsfargo.stockmarket.stockexchange.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wellsfargo.stockmarket.stockexchange.entity.CompanyToStockExchangeMapper;

public interface CompanyToStockExchangeMapperRepository extends JpaRepository<CompanyToStockExchangeMapper, String > {

	List<CompanyToStockExchangeMapper> findAllByStockExchange(String stockExchange);
	
}
