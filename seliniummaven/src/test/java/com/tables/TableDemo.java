package com.tables;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.factory.DriverFactory;

public class TableDemo {

WebDriver driver;
	
	@BeforeClass
	public void setup() {
		driver = DriverFactory.getDriver();
	}
	
	@Test
	public void testBadSSL() throws InterruptedException {
		
		Thread.sleep(5000);
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
