package com.wellsfargo.stockmarket.stockdataretrieval;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepo extends JpaRepository<Sector,Integer> {

}
