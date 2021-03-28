package com.datadriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestAddressesFromDataProvierUsingList {

WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		// Get the project root directory using user.dir system property
		String userDir = System.getProperty("user.dir");
		//setting Chrome driver executable path or use WebDriverManager class
		//System.setProperty("webdriver.chrome.driver", userDir+"/src/test/resources/drivers/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		//Initializing WebDriver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider = "addressDataProviderUsingList" ,  dataProviderClass = AddressDataUsingLinkedList.class)
	public void addAddressFromDataProvider(String testCaseName, String firstName, String lastName) throws InterruptedException  {

		driver.get("http://a.testaddressbook.com/");

		WebElement signIn = driver.findElement(By.xpath("/html/body/nav/div/div[1]/a[2]"));
		signIn.click();
		
		WebElement subTitle = driver.findElement(By.xpath("//body//h2"));
		Assert.assertEquals(subTitle.getText(), "Sign in");
		
		Thread.sleep(2000);

		WebElement email = driver.findElement(By.xpath("//input[@class ='form-control']"));
		email.sendKeys("avinash.vcentry@gmail.com");
		
		Thread.sleep(2000);
		
		WebElement password = driver.findElement(By.xpath("(//input[@class='form-control'])[2]"));
		password.sendKeys("vcentry2021");

		Thread.sleep(2000);
	
		WebElement signInButton= driver.findElement(By.xpath("//input[contains(@class,'btn-primary')]"));
		signInButton.click();

		Thread.sleep(3000);
		
		WebElement addressesLink= driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));
		addressesLink.click();

		WebElement newAddressLink= driver.findElement(By.xpath("//a[starts-with(@href,'/addresses/n')]"));
		newAddressLink.click();

		Thread.sleep(2000);

		//Chained 
		WebElement firstNameField = driver.findElement(By.xpath("//div[contains(@class,'form-group row')]//input[@id='address_first_name']"));
		firstNameField.sendKeys(firstName);

		Thread.sleep(5000);
		
		//Chained 
		WebElement lastNameField = driver.findElement(By.xpath("//input[@id='address_last_name']"));
		lastNameField.sendKeys(lastName);

		Thread.sleep(5000);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	
}
