package com.wellsfargo.stockmarket.stockdataretrieval;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wellsfargo.stockmarket.stockdataretrieval.model.Sector;
import com.wellsfargo.stockmarket.stockdataretrieval.service.SectorService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTesting {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SectorService sectorService;
	
	@Test
	@DisplayName("Test all sectors found - GET /sectors")
	public void testAllSectorsFound() throws Exception{
		Sector firstSector = new Sector(1L,"first sector","this is first sector");
		
		List<Sector> sectors = new ArrayList<>();
		sectors.add(firstSector);
		
		doReturn(sectors).when(sectorService).getAllSectors();

		mockMvc.perform(MockMvcRequestBuilders.get("/sectors"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$[0].sectorName",is("first sector")));
	}
	
	@Test
	@DisplayName("Test Sector Found - GET /sectors/1")
	public void testGetSectorById() throws Exception{
		
		Sector mockSector = new Sector(1L,"mock","this is mock sector");
		doReturn(mockSector).when(sectorService).getSector(mockSector.getSectorId());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/sectors/{sectorid}", 1))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(jsonPath("$.sectorId",is(1)))
		.andExpect(jsonPath("$.sectorName",is("mock")))
		.andExpect(jsonPath("$.sectorBrief",is("this is mock sector")));
	}
	

}
