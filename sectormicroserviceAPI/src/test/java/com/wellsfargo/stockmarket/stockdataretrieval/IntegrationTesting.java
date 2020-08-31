package com.wellsfargo.stockmarket.stockdataretrieval;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

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
	@DisplayName("Test Sector Found - GET /sectors/1")
	public void testGetSectorById() throws Exception{
		
		Sector mockSector = new Sector(1L,"mock","this is mock sector");
		Mockito.when(sectorService.getSector(1L)).thenReturn(mockSector);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sectors/102").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "{'sectorId':1,'sectorName':'mock','sectorBrief':'this is mock sector'}";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),false);
	}

}
