package com.basics.identifiers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NumberOfLinksInThePage {
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		// Get the project root directory using user.dir system property
		String userDir = System.getProperty("user.dir");
		//setting Chrome driver executable path
		System.setProperty("webdriver.chrome.driver", userDir+"/src/test/resources/drivers/chromedriver.exe");
		//Initializing WebDriver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void numberOfLinks() throws InterruptedException  {

		driver.get("http://a.testaddressbook.com/");
		
		List<WebElement> anchorTags = driver.findElements(By.tagName("a"));
		System.out.printf("Number of Links in the page with By.TagName() - %d\n" , anchorTags.size());
		
		List<WebElement> anchorTagsWithXpath = driver.findElements(By.xpath("//a"));
		System.out.printf("Number of Links in the page with xpath -  %d\n" , anchorTagsWithXpath.size());
		
		List<WebElement> anchorTagsWithCSS = driver.findElements(By.cssSelector("a"));
		System.out.printf("Number of Links in the page with CSS -  %d\n" ,anchorTagsWithCSS.size());

		Thread.sleep(5000);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
