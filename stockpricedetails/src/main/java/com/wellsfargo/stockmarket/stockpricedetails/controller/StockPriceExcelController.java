package com.wellsfargo.stockmarket.stockpricedetails.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wellsfargo.stockmarket.stockpricedetails.entity.StockPriceExcelEntity;
import com.wellsfargo.stockmarket.stockpricedetails.response.ResponseMessage;
import com.wellsfargo.stockmarket.stockpricedetails.service.StockPriceExcelService;

@RestController
public class StockPriceExcelController {
	@Autowired
	private StockPriceExcelService upload;
	private List<StockPriceExcelEntity> s;

	// Run in browser using http://localhost:3003/upload and set a key with name file and values as the excel sheet
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws Throwable {
		String message = "";

		if (upload.hasExcelFormat(file)) {
			try {
				upload.save(file);

				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

			}
		}
		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));

	}
}
