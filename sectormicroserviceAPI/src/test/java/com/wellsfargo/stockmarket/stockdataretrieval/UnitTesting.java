package com.wellsfargo.stockmarket.stockdataretrieval;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wellsfargo.stockmarket.stockdataretrieval.model.Sector;
import com.wellsfargo.stockmarket.stockdataretrieval.service.SectorService;


public class UnitTesting {

	@Test
	void getAllSectorTest() {
		SectorService serv = mock(SectorService.class);
		
		List<Sector> sector = serv.getAllSectors();
		assertNotNull(sector);
		verify(serv).getAllSectors();
	}

}
