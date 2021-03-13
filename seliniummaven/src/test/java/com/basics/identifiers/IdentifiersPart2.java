package com.basics.identifiers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IdentifiersPart2 {
	
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
	public void identifiers2() throws InterruptedException  {

		driver.get("http://a.testaddressbook.com/");
		
		WebElement signIn = driver.findElement(By.id("sign-in"));
		signIn.click();

		WebElement email = driver.findElement(By.cssSelector(".form-control"));
		email.sendKeys("avinash.vcentry@gmail.com");
		
		WebElement password = driver.findElement(By.name("session[password]"));
		password.sendKeys("vcentry2021");
		
		WebElement signInButton= driver.findElement(By.className("btn-primary"));
		signInButton.click();
		
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
