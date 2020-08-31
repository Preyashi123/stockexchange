package com.wellsfargo.stockmarket.stockpricedetails.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.wellsfargo.stockmarket.stockpricedetails.entity.StockPriceExcelEntity;

public class ServiceHelperReadExcel {

	public static final String SAMPLE_XLSX_FILE_PATH = "C:\\Users\\admin\\Documents\\demo\\src\\main\\resources\\sample_stock_data.xlsx";

	public static List<StockPriceExcelEntity> readCellValues(InputStream inputStream) throws IOException, InvalidFormatException {
		Workbook workbook;

//		Workbook workbook = new XSSFWorkbook();
		
		workbook = WorkbookFactory.create(inputStream);

		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

		// Iterating all the sheets in the workbook

		// 1. You can obtain a sheetIterator and iterate over it
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		System.out.println("Retrieving Sheets using Iterator");
		while (sheetIterator.hasNext()) {
			Sheet sheet = sheetIterator.next();
			System.out.println("=> " + sheet.getSheetName());
		}

		List<StockPriceExcelEntity> stockList = new ArrayList<StockPriceExcelEntity>();

		/*
		 * Iterating over all the rows and columns in a Sheet
		 */

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);
//		System.out.println(sheet.getPhysicalNumberOfRows());

		// for-each loop to iterate over the rows and columns

		int counterCell = 0, counterRow = 1;

		for (Row row : sheet) {
			if (row.getFirstCellNum() == -1)
				continue;
			if (counterRow == 1) {
				counterRow = -1;
				continue;
			}
			StockPriceExcelEntity stock = new StockPriceExcelEntity();
			counterCell = 0;
			for (Cell cell : row) {
				switch (cell.getCellTypeEnum()) {
				// check if cell values in excel sheet are numeric type or string type
				case STRING:
					switch (counterCell) {
					// Get cell values and set the values of StockPriceEntity class by calling it's setter methods
					case 0:
						String s1 = ((String) cell.getStringCellValue());
						String s2 = "";
						for (char c : s1.toCharArray()) {
							if (c >= '0' && c <= '9')
								s2 = s2 + c;
						}
						double code = Double.valueOf(s2);
						stock.setCompanyCode(code);
						break;
					case 1:
						stock.setStockExchange(cell.getRichStringCellValue().getString());
						break;

					case 4:
						stock.setStockPriceTime((String) cell.getStringCellValue());
						break;
					}
					break;
				case NUMERIC:
					switch (counterCell) {

					case 2:
						stock.setCurrentPrice((double) cell.getNumericCellValue());
						break;

					case 3:
						stock.setStockPriceDate((Date) cell.getDateCellValue());
						break;

					}
					break;
				default:
					break;

				}
				counterCell++;

			}

			stockList.add(stock);
		}

		workbook.close();
		return stockList;
	}

}
