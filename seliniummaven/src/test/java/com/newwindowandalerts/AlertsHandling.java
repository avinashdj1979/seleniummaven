package com.newwindowandalerts;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertsHandling {
WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		// Get the project root directory using user.dir system property
		String userDir = System.getProperty("user.dir");
		//setting Chrome driver executable path or use WebDriverManager class
		//System.setProperty("webdriver.chrome.driver", userDir+"/src/test/resources/drivers/chromedriver.exe");
		WebDriverManager.firefoxdriver().setup();
		//Initializing WebDriver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void alertsHandling() throws InterruptedException  {

		driver.get("https://demoqa.com/alerts");
		
		WebElement alertButton = driver.findElement(By.xpath("//button[@id='alertButton']"));
		alertButton.click();
		
		Alert alert = driver.switchTo().alert();
		Thread.sleep(5000);
		alert.accept();
		 
		WebElement timerAlertButton = driver.findElement(By.xpath("//button[@id='timerAlertButton']"));
		timerAlertButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert timerAlert = driver.switchTo().alert();
		System.out.println(alert.getText());
		Thread.sleep(5000);
		timerAlert.accept();
		
		WebElement okCancelAlertButton = driver.findElement(By.cssSelector("#confirmButton"));
		okCancelAlertButton.click();
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert okCancelAlert = driver.switchTo().alert();
		Thread.sleep(5000);
		okCancelAlert.accept();
		
		okCancelAlertButton.click();
		wait.until(ExpectedConditions.alertIsPresent());
		
		okCancelAlert = driver.switchTo().alert();
		Thread.sleep(5000);
		okCancelAlert.dismiss();
		
		WebElement promptAlertButton = driver.findElement(By.xpath("(//button[contains(@class,'btn')])[4]"));
		promptAlertButton.click();
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert promptAlert = driver.switchTo().alert();
		Thread.sleep(5000);
		promptAlert.sendKeys("Typing text");
		Thread.sleep(5000);
		promptAlert.accept();
		
		Thread.sleep(5000);
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
