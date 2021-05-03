package com.autoit;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.factory.DriverFactory;

public class FileUploadUsingAutoIT {
	
	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = DriverFactory.getDriver();
	}

	@Test
	public void testFileUploadUsingAutoIT() throws InterruptedException {
		driver.get("http://djangovinoth.pythonanywhere.com/login/");

		WebElement login = driver.findElement(By.name("username"));
		login.sendKeys("avinashdj");

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("Sadu2006$");

		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.click();

		Thread.sleep(5000);
		
		driver.get("http://djangovinoth.pythonanywhere.com/FileUploadDownload/");

//		driver.get("https://demoqa.com/automation-practice-form");
//
//		Thread.sleep(5000);
//		
//		WebElement chooseFile = driver.findElement(By.id("uploadPicture"));

		
		WebElement chooseFile = driver.findElement(By.name("newfile"));
		
		Actions action = new Actions(driver);
		action.moveToElement(chooseFile).click().build().perform();
		
		Thread.sleep(5000);

		String exe = System.getProperty("user.dir") + "\\src\\test\\resources\\FileUpload.exe";
		
		String fileToUpload = System.getProperty("user.dir") + "\\src\\test\\resources\\sample.txt";
		
		try {
			Runtime.getRuntime().exec(exe + " " + fileToUpload);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Thread.sleep(15000);
		
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
