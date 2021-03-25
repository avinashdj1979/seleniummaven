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
		// setting Chrome driver executable path or use WebDriverManager class
		// System.setProperty("webdriver.chrome.driver",
		// userDir+"/src/test/resources/drivers/chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		// Initializing WebDriver
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@Test
	public void jsExecutor() throws InterruptedException {

		driver.get("http://a.testaddressbook.com/");

		WebElement signInLink = driver.findElement(By.id("sign-in"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", signInLink);

		WebElement email = driver.findElement(By.xpath("//input[@class ='form-control']"));

		WebElement password = driver.findElement(By.xpath("//input[@id='session_password']"));

		js.executeScript("arguments[0].value='avinash.vcentry@gmail.com';", email);

		js.executeScript("arguments[0].value='vcentry2021';", password);

		WebElement submit = driver.findElement(By.name("commit"));

		js.executeScript("arguments[0].click();", submit);

		Thread.sleep(5000);

		WebElement addressesLink = driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));
		addressesLink.click();

		WebElement newAddressLink = driver.findElement(By.xpath("//a[starts-with(@href,'/addresses/n')]"));
		newAddressLink.click();

		js.executeScript("window.scrollBy(0,700);");
		Thread.sleep(5000);

		js.executeScript("window.scrollBy(0,-700);");
		Thread.sleep(5000);

		WebElement notes = driver.findElement(By.id("address_note"));

		js.executeScript("arguments[0].scrollIntoView();", notes);

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", notes);

		Thread.sleep(5000);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
