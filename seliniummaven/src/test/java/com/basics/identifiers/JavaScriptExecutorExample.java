package com.basics.identifiers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutorExample {
WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		// Get the project root directory using user.dir system property
		String userDir = System.getProperty("user.dir");
		//setting Chrome driver executable path or use WebDriverManager class
		//System.setProperty("webdriver.chrome.driver", userDir+"/src/test/resources/drivers/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		//Initializing WebDriver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void jsExecutor() throws InterruptedException  {

		driver.get("http://a.testaddressbook.com/");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement home = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
		WebElement signIn = driver.findElement(By.xpath("/html/body/nav/div/div[1]/a[2]"));
		Thread.sleep(2000);//

		js.executeScript("arguments[0].classList.remove(\"active\");", home);
		
		Thread.sleep(2000);
		
		js.executeScript("arguments[0].classList.add(\"active\");", signIn);
		js.executeScript("arguments[0].click();", signIn);
		
		Thread.sleep(5000);

		

		//Tag Attribute value trio
		//tagName[@atrib='value']
		//$x("//input[@class='form-control']")
		WebElement email = driver.findElement(By.xpath("//input[@class ='form-control']"));
		//email.sendKeys("avinash.vcentry@gmail.com");
		js.executeScript("arguments[0].value='avinash.vcentry@gmail.com';", email);
		
		Thread.sleep(2000);

		//Mutliple tags with attribute and Values
		WebElement password = driver.findElement(By.xpath("//input[@type='password'][@name='session[password]']"));
		//WebElement password = driver.findElement(By.xpath("//input[@id='session_password']"));
		//password.sendKeys("vcentry2021");
		js.executeScript("arguments[0].value='vcentry2021';", password);
		Thread.sleep(2000);
		
		//Contains
		WebElement signInButton= driver.findElement(By.xpath("//input[contains(@class,'btn-primary')]"));
		//signInButton.click();
		

		js.executeScript("arguments[0].click();", signInButton);
		
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
