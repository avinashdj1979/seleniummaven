package com.basics.usingtestng;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IEEdgeUsingTestNG {
	WebDriver driver;

	@BeforeClass
	public void setUp() {
//		// Get the project root directory using user.dir system property
//		String userDir = System.getProperty("user.dir");
//		// setting Chrome driver executable path
//		System.setProperty("webdriver.chrome.driver", userDir + "/src/test/resources/drivers/chromedriver.exe");
//		// System.setProperty("webdriver.edge.driver", "C:\\selenium\\drivers\\msedgedriver.exe");
		/*
		 * Replacing line number 20 to 23 with line number 28.
		 */
		WebDriverManager.edgedriver().setup();
		// Initializing WebDriver
		driver = new EdgeDriver();
	}

	@Test
	public void ieedgeTest()  {
		driver.get("https://www.google.com");
		WebElement q = driver.findElement(By.name("q"));
		q.sendKeys("Women's day");
		q.sendKeys(Keys.ENTER);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
