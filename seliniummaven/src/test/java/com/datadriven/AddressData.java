package com.datadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class AddressData {

	Object[][] testData;
	FileInputStream fis;
	String userDir = System.getProperty("user.dir");
	String filePath = userDir + "\\src\\test\\resources\\testdata\\TestData.xlsx";
	XSSFWorkbook addressWB;

	@DataProvider(name = "addressDataProvider")
	public Object[][] getAdressData() {
		try {
			fis = new FileInputStream(filePath);
			addressWB = new XSSFWorkbook(fis);
			int noOfSheets = addressWB.getNumberOfSheets();
			getRowsAndColumns(addressWB, "Addresses");
			for (int i = 0; i < noOfSheets; i++) {
				if (addressWB.getSheetName(i).equalsIgnoreCase("Addresses")) {
					XSSFSheet addressesWS = addressWB.getSheetAt(i);
					Iterator<Row> ri = addressesWS.rowIterator();
					Row headingRow = ri.next();
					Row contentRow;
					Iterator<Cell> ic;
					int rowCount = 0;
					while (ri.hasNext()) {
						contentRow = ri.next();
						ic = contentRow.cellIterator();
						int columnCount = 0;
						if (ic.next().getStringCellValue().equalsIgnoreCase("Yes")) {
							while (ic.hasNext()) {
								String value = ic.next().getStringCellValue();
								// System.out.println(value);
								testData[rowCount][columnCount] = value;
								columnCount++;
							}
						}
						rowCount++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < testData.length; i++) {
			for (int j = 0; j < testData[i].length; j++) {
				System.out.println(testData[i][j].toString());
			}
		}
		return testData;
	}

	public void getRowsAndColumns(XSSFWorkbook addressWB, String sheetName) {
		int rows = 0;
		int cols = 0;
		Row row;
		int noOfSheets = addressWB.getNumberOfSheets();
		for (int i = 0; i < noOfSheets; i++) {
			if (addressWB.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet addressesWS = addressWB.getSheetAt(i);
				Iterator<Row> ri = addressesWS.rowIterator();
				Row headerRow = ri.next();
				cols = headerRow.getLastCellNum();
				while (ri.hasNext()) {
					row = ri.next();
					if (row.getCell(i).getStringCellValue().equalsIgnoreCase("Yes")) {
						rows = rows + 1;
					}
				}
			}
		}

		testData = new Object[rows][cols - 1];
	}

	public static void main(String args[]) {
		AddressData addressData = new AddressData();
		addressData.getAdressData();
	}
}
