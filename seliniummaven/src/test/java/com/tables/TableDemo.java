package com.tables;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.factory.DriverFactory;

public class TableDemo {

	WebDriver driver;

	@BeforeClass
	public void setup() {
		driver = DriverFactory.getDriver();
	}

	@Test
	public void testTable() throws InterruptedException {
		driver.get("http://djangovinoth.pythonanywhere.com/login/");

		WebElement login = driver.findElement(By.name("username"));
		login.sendKeys("avinashdj");

		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("Sadu2006$");

		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		submit.click();

		Thread.sleep(5000);

		driver.get("http://djangovinoth.pythonanywhere.com/dynamictable2/");

		Thread.sleep(5000);

		WebElement tableHeader = driver.findElement(By.xpath("//table[@id='table1']//thead"));

		List<WebElement> colHeadings = tableHeader.findElements(By.xpath("./tr/child::th"));

		for (WebElement colHeading : colHeadings) {
			System.out.println(colHeading.getText());
		}

		WebElement tableBody = driver.findElement(By.xpath("//table[@id='table1']//tbody"));

		List<WebElement> rows = tableBody.findElements(By.xpath("./child::tr"));

		int noOfRows = rows.size();
		
		System.out.println(noOfRows);
		
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.xpath("./child::td"));
			if ((cols.get(1).getText()).equals("user2")) {
				cols.get(0).click();
				driver.findElement(By.xpath("//button[text()='Delete Row']")).click();
			}
		}
		
		Thread.sleep(3000);
		
		int rowsAfterDelete = tableBody.findElements(By.xpath("./child::tr")).size();
		
		System.out.println(rowsAfterDelete);

		Assert.assertTrue(((noOfRows - 1) == rowsAfterDelete), "Delete action is not successful");
		
		//Add User
		
		driver.findElement(By.id("name")).sendKeys("user4");
		driver.findElement(By.id("email")).sendKeys("user4@mail.com");
		driver.findElement(By.id("rowid")).click();
		
		Thread.sleep(5000);
		
		tableBody = driver.findElement(By.xpath("//table[@id='table1']//tbody"));

		rows = tableBody.findElements(By.xpath("./child::tr"));

		noOfRows = rows.size();
		
		System.out.println(noOfRows);
		
		boolean userFound = false;
		
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.xpath("./child::td"));
			if ((cols.get(1).getText()).equals("user5")) {
				userFound = true;
				break;
			}
		}
		
		Thread.sleep(3000);
		
		Assert.assertTrue(userFound, "User4 is not added");
		
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
