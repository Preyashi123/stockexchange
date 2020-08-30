package com.wellsfargo.stockmarket.stockdataretrieval;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer> {

	List<Company> findBySectorName(String name);


}
