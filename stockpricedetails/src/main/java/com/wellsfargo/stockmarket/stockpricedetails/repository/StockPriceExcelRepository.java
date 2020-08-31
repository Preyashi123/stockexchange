package com.wellsfargo.stockmarket.stockpricedetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wellsfargo.stockmarket.stockpricedetails.entity.StockPriceExcelEntity;

@Repository
public interface StockPriceExcelRepository extends JpaRepository<StockPriceExcelEntity, Integer> {
}

