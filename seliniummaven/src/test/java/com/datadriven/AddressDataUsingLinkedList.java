package com.datadriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.ss.formula.functions.Rows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class AddressDataUsingLinkedList {

	Object[][] testData;
	FileInputStream fis;
	String userDir = System.getProperty("user.dir");
	String filePath = userDir + "\\src\\test\\resources\\testdata\\TestData.xlsx";
	XSSFWorkbook addressWB;

	public LinkedList<LinkedList<String>> getDataFromExcel() {
		LinkedList<LinkedList<String>> addressList = new LinkedList<LinkedList<String>>();
		try {
			fis = new FileInputStream(filePath);
			addressWB = new XSSFWorkbook(fis);
			int noOfSheets = addressWB.getNumberOfSheets();
			for (int i = 0; i < noOfSheets; i++) {
				if (addressWB.getSheetName(i).equalsIgnoreCase("Addresses")) {
					XSSFSheet addressesWS = addressWB.getSheetAt(i);
					Iterator<Row> ri = addressesWS.rowIterator();
					Row headingRow = ri.next();
					Row contentRow;
					Iterator<Cell> ic;
					while (ri.hasNext()) {
						LinkedList<String> temp = new LinkedList<String>();
						contentRow = ri.next();
						ic = contentRow.cellIterator();
						if (ic.next().getStringCellValue().equalsIgnoreCase("Yes")) {
							while (ic.hasNext()) {
								String value = ic.next().getStringCellValue();
								temp.add(value);
							}
						}
						addressList.add(temp);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressList;
	}
	
	@DataProvider(name="addressDataProviderUsingList")
	public Object[][] getAddressData() {
		LinkedList<LinkedList<String>> addressList = getDataFromExcel();
		int rowCount = addressList.size();
		int colCount = addressList.get(0).size();
		testData = new Object[rowCount][colCount];
		int rowCounter = 0;
		int colCounter = 0;
		for (LinkedList rowval : addressList) {
			for (Object value: rowval) {
				testData[rowCounter][colCounter] = value;
				colCounter = colCounter + 1;
			}
			colCounter = 0;
			rowCounter = rowCounter+1;
		}
		for (int i = 0; i < testData.length; i++) {
			for (int j = 0; j < testData[i].length; j++) {
				System.out.println(testData[i][j].toString());
			}
		}
		return testData;
	}

	public static void main(String args[]) {
		AddressDataUsingLinkedList addressData = new AddressDataUsingLinkedList();
		addressData.getAddressData();
	}
}
