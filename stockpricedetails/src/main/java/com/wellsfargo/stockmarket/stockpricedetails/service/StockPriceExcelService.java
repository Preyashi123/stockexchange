package com.wellsfargo.stockmarket.stockpricedetails.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wellsfargo.stockmarket.stockpricedetails.entity.StockPriceExcelEntity;
import com.wellsfargo.stockmarket.stockpricedetails.repository.StockPriceExcelRepository;


@Service
public class StockPriceExcelService {

	 @Autowired
	 StockPriceExcelRepository repository;
	 
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	 
	 public boolean hasExcelFormat(MultipartFile file) {

			if(file==null) {
				System.out.println("No file");
			return false;}
		    if (!TYPE.equals(file.getContentType())) {
		      return false;
		    }

		    return true;
		  }
	    

	  public List<StockPriceExcelEntity> save(MultipartFile file) throws Throwable {
		  try {
	    List<StockPriceExcelEntity> result = ServiceHelperReadExcel.readCellValues(file.getInputStream());
	      for(StockPriceExcelEntity s:result) {
				System.out.println(s.getCompanyCode());
			}
	      repository.saveAll(result);
	      return result;
		  } catch (IOException e) {
	      throw new RuntimeException("fail to store excel data: " + e.getMessage());
	    }
	  }
}
