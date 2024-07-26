package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.qa.opencart.logger.Log;

public class ExcelUtil {
	
	private static final String EXCEL_FILE_PATH = "./src/test/resources/testdata/OpencartTestData.xlsx";
	private static FileInputStream fip;
	private static Workbook workbook;
	private static Sheet sheet;
	
	public static Object[][] getTestData (String sheetName) {
		
		Log.info("Getting data from the sheet : " + sheetName);
		Object[][] data = null;
		
		try {
			fip = new FileInputStream(EXCEL_FILE_PATH);
			workbook = WorkbookFactory.create(fip);
			sheet = workbook.getSheet(sheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		
		return data;
	}

}
