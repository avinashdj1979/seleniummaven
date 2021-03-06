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

public class AddressDataUsingIterator {

	Object[][] testData;
	FileInputStream fis;
	String userDir = System.getProperty("user.dir");
	String filePath = userDir + "\\src\\test\\resources\\testdata\\TestData.xlsx";
	XSSFWorkbook addressWB;
	
	@DataProvider(name="addressDataProviderUsingIterator")
	public Iterator<Object> getIteratorOfObjectArray() {
		LinkedList<LinkedList<String>> addressList = new LinkedList<LinkedList<String>>();
		LinkedList<Object> objList = new LinkedList<Object>();
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
						Object o =  new Object();
						o = (Object) temp;
						objList.add(o);
  					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objList.iterator();
	}
}
