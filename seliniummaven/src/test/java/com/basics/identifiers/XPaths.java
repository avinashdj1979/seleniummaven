package com.basics.identifiers;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.constants.UIConstants;
import com.utils.PropertyReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XPaths {
WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		String fileName = "/src/test/resources/config/config2.txt";
		PropertyReader pr = new PropertyReader();
		HashMap<String,String> properties = pr.getProperties();
		if(properties.get(UIConstants.BROWSER).equals(UIConstants.CHROME)) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); 
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(properties.get(UIConstants.IMPLICITLY_WAIT_TIME)), TimeUnit.SECONDS);
	}
	
	@Test
	public void xPaths() throws InterruptedException  {

		driver.get("http://a.testaddressbook.com/");

		//Absolute Path
		//Start with a single slash to start from the root of the DOM
		WebElement signIn = driver.findElement(By.xpath("/html/body/nav/div/div[1]/a[2]"));
		signIn.click();
		
//		List<WebElement> aLists = driver.findElements(By.xpath("/html/body/nav/div/div[1]/a"));
//		aLists.get(1).click();

		
		//Absolute Path
		//Start from anywhere in HTML DOM and skip the elements in the middle
		///html/body/div/div/h2
		WebElement subTitle = driver.findElement(By.xpath("//body//h2"));
		Assert.assertEquals(subTitle.getText(), "Sign in");
		
		Thread.sleep(2000);

		//Tag Attribute value trio
		//tagName[@atrib='value']
		//$x("//input[@class='form-control']")
		WebElement email = driver.findElement(By.xpath("//input[@class ='form-control']"));
		email.sendKeys("avinash.vcentry@gmail.com");
		
		Thread.sleep(2000);

		//Mutliple tags with attribute and Values
		//WebElement password = driver.findElement(By.xpath("//input[@type='password'][@name='session[password]']"));
		//WebElement password = driver.findElement(By.xpath("//input[@id='session_password']"));
		WebElement password = driver.findElement(By.xpath("(//input[@class='form-control'])[2]"));
		password.sendKeys("vcentry2021");

		Thread.sleep(2000);
		
		//Contains
		WebElement signInButton= driver.findElement(By.xpath("//input[contains(@class,'btn-primary')]"));
		signInButton.click();

		Thread.sleep(10000);
		
		//Contains() and text()
		WebElement addressesLink= driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));
		addressesLink.click();
		
		//text() alone
		WebElement addressesListHeader= driver.findElement(By.xpath("//h2[text() = 'Addresses']"));
		System.out.printf("Addresses List Header value - %s\n", addressesListHeader.getText());
		
		//Starts-With
		WebElement newAddressLink= driver.findElement(By.xpath("//a[starts-with(@href,'/addresses/n')]"));
		newAddressLink.click();
		
		Thread.sleep(2000);
		
		//Chained 
		WebElement firstName= driver.findElement(By.xpath("//div[contains(@class,'form-group row')]//input[@id='address_first_name']"));
		firstName.sendKeys("Rajesh ");
		
		
		
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
