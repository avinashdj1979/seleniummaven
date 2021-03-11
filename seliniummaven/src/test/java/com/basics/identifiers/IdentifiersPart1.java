package com.basics.identifiers;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IdentifiersPart1 {
	
	WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		// Get the project root directory using user.dir system property
		String userDir = System.getProperty("user.dir");
		//setting Chrome driver executable path
		System.setProperty("webdriver.chrome.driver", userDir+"/src/test/resources/drivers/chromedriver.exe");
		//Initializing WebDriver
		driver = new ChromeDriver();
	}
	
	@Test
	public void identifiers1() throws InterruptedException  {

		driver.get("http://djangovinoth.pythonanywhere.com/labhome/");
		
		WebElement login = driver.findElement(By.partialLinkText("Login"));
		login.click();
		
		WebElement userName = driver.findElement(By.name("username"));
		userName.sendKeys("avinashdj");
		
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("Sadu2006$");
		
		WebElement loginButton= driver.findElement(By.xpath("//button[@type='submit']"));
		loginButton.click();
		
		WebElement logout = driver.findElement(By.linkText("Logout"));
		logout.click();
		
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
