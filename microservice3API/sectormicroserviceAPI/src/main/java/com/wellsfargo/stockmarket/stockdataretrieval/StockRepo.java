package com.wellsfargo.stockmarket.stockdataretrieval;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<Stock,Integer> {

	List<Stock> findByCompanyName(String companyName);

}
