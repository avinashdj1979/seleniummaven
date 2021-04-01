package com.badssl;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.factory.DriverFactory;

public class BadSSLTest {
	
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		driver = DriverFactory.getDriver();
	}
	
	@Test
	public void testBadSSL() throws InterruptedException {
		driver.get("https://expired.badssl.com/");
		Thread.sleep(5000);
	}
	
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
