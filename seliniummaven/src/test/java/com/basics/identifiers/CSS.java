package com.basics.identifiers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CSS {
WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		// Get the project root directory using user.dir system property
		String userDir = System.getProperty("user.dir");
		//setting Chrome driver executable path
		System.setProperty("webdriver.chrome.driver", userDir+"/src/test/resources/drivers/chromedriver.exe");
		//Initializing WebDriver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void css() throws InterruptedException  {

		driver.get("http://a.testaddressbook.com/");

		//Absolute CSS
		WebElement signIn = driver.findElement(By.cssSelector("html>body>nav>div>div>a:nth-child(2)"));
		signIn.click();
		
		// not cotiaining a specific class
		//WebElement signIn = driver.findElement(By.cssSelector("a.nav-item.nav-link:not(.active)"));
		//signIn.click();
		
		//Absolute CSS - 
		//Start from anywhere in HTML DOM and skip the elements in the middle
		WebElement subTitle = driver.findElement(By.cssSelector("body div h2"));
		Assert.assertEquals(subTitle.getText(), "Sign in");
		
		//Tag and className
		WebElement email = driver.findElement(By.cssSelector("input.form-control"));
		email.sendKeys("avinash.vcentry@gmail.com");

		//Tag and id
		WebElement password = driver.findElement(By.cssSelector("input#session_password"));
		password.sendKeys("vcentry2021");
		
		Thread.sleep(2000);
		
		//Tag and Attribute
		WebElement signInButton= driver.findElement(By.cssSelector("input[type='submit']"));
		signInButton.click();

		Thread.sleep(5000);
		
		//Contains
		WebElement addressesLink= driver.findElement(By.cssSelector("a[href*='addresses']"));
		addressesLink.click();
		
		Thread.sleep(2000);
		
		//Starts-With
		WebElement newAddressLink= driver.findElement(By.cssSelector("a[href^='/addresses/n']"));
		newAddressLink.click();
		
		Thread.sleep(2000);
		
		//First of Type
		WebElement firstName= driver.findElement(By.cssSelector("input[id^='address_']:first-of-type"));
		firstName.sendKeys("Rajesh");
		
		Thread.sleep(2000);
		
		//Ends-with
		WebElement lastName= driver.findElement(By.cssSelector("input[id$='last_name']"));
		lastName.sendKeys("Kumar");
		
		Thread.sleep(2000);
		
		//nth-child
		WebElement address1= driver.findElement(By.cssSelector("form#new_address div:nth-child(5) input"));
		address1.sendKeys("233 Colony at Beer Creek");
		
		Thread.sleep(2000);
		
		//Sibling
		WebElement city= driver.findElement(By.cssSelector("label[for='address_city'] + input"));
		city.sendKeys("Redmond");
		
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
