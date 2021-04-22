package com.datadriven;

import java.io.File;
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
	File f;
	FileInputStream fis;
	String userDir = System.getProperty("user.dir");
	String filePath = userDir + "\\src\\test\\resources\\testdata\\TestData.xlsx";
	XSSFWorkbook wb;
	XSSFSheet ws;
	LinkedList<LinkedList<String>> addressData = new LinkedList<LinkedList<String>>();
	
	public LinkedList<LinkedList<String>> getLinkedList() {
		try {
			f = new File(filePath);
			fis = new FileInputStream(f);
			wb = new XSSFWorkbook(fis);
			
			//Number of worksheets to iterate and find the reqd worksheet
			
			int noOfSheets = wb.getNumberOfSheets();
			System.out.printf("Number of sheets - %d\n", noOfSheets);
			
			for(int i = 0;i < noOfSheets;i++) {
				if(wb.getSheetName(i).equalsIgnoreCase("addresses")) {
					System.out.println("Sheet found");
					ws = wb.getSheetAt(i);
				} else {
					System.out.println("Sheet not found");
				}
			}
			
			Iterator<Row> ri = ws.rowIterator();
			ri.next();
			
			while(ri.hasNext()) {
				LinkedList<String> temp = new LinkedList<String>();
				Row r = ri.next();
				Iterator<Cell> ci = r.cellIterator();
				Cell execute = ci.next();
				if(execute.getStringCellValue().equalsIgnoreCase("Yes")) {
					while(ci.hasNext()) {
						Cell c = ci.next();
					    System.out.println(c.getStringCellValue());
					    temp.add(c.getStringCellValue());
					}
				}
				addressData.add(temp);
			}
			System.out.println(addressData);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return addressData;
	}
	
	@DataProvider(name = "myTestData")
	public Object[][] getTestData() {
		LinkedList<LinkedList<String>> temp = getLinkedList();
		int rc = temp.size();
		System.out.println(rc);
		int cc = temp.get(0).size();
		System.out.println(cc);
		Object[][] testDataArr = new Object[rc][cc];
		
		for(int i =0; i < rc; i++) {
			for(int j=0; j < cc; j++) {
				testDataArr[i][j] = temp.get(i).get(j);
			}
		}
		System.out.println("******************");
		System.out.println("Priniting 2d object arr");
		for(int i =0; i < rc; i++) {
			for(int j=0; j < cc; j++) {
				System.out.print(testDataArr[i][j] + "||");
			}
			System.out.println();
		}
		
		return testDataArr;
	}
	
	public static void main(String args[]) {
		AddressDataUsingLinkedList addressData = new AddressDataUsingLinkedList();
		addressData.getTestData();
	}
	
	
	
	
	
	
	
	
	
	
	
	

	/*public LinkedList<LinkedList<String>> getDataFromExcel() {
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
						Cell execute = ic.next();
						if(execute.getStringCellValue().equalsIgnoreCase("Yes")) {
							while (ic.hasNext()) {
								Cell currentCell = ic.next();
								String value = currentCell.getStringCellValue();
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
	
	
	//int[] arr = new int [5];  // {1,2,3,4,5} - 1D Array
	//int[][] arr1 = new int[5][3]; // 
	//int[][] arr1 = {{1,2,4}, {2,3,5}, {3,4,6}, {40,50,6}, {69,51,7}} - 2D Array
	
	//Object[][] obj;
	
	@DataProvider(name = "addressDataProviderUsingList")
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
	}*/


}
