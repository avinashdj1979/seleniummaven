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

public class TableDemo1 {

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
		
		WebElement tableBody = driver.findElement(By.xpath("//table[@id='table1']//tbody"));
		
		List<WebElement> rows = tableBody.findElements(By.xpath("./child::tr"));
		
		int noOfRows = rows.size();
		
		System.out.println(noOfRows);
		
//		for(WebElement row : rows) {
//			List<WebElement> cols = row.findElements(By.xpath("./child::td"));
////			for(WebElement col : cols) {
////				System.out.println(col.getText());
////			}
//			if(cols.get(1).getText().equals("user2")) {
//				cols.get(0).click();
//				driver.findElement(By.xpath("//button[text()='Delete Row']")).click();
//				break;
//			}
//		}
		
		//Line no 50 to 60 can be replaced by code below
		try {
			WebElement select = driver.findElement(By.xpath("//td[text()='user2']/preceding-sibling::td//input"));
			select.click();
			driver.findElement(By.xpath("//button[text()='Delete Row']")).click();
		} catch(Exception e) {
			Assert.fail("User2 not found");
		}
		
		Thread.sleep(3000);
		
		int rowsAfterDelete = tableBody.findElements(By.xpath("./child::tr")).size();
		
		System.out.println(rowsAfterDelete);
		
		Assert.assertEquals(rowsAfterDelete , (noOfRows-1), "User to be deleted does not exists in the table");
		
		//Assert.assertTrue((rowsAfterDelete == (noOfRows-1)), "User to be deleted does not exists in the table")
		
		// Add User
		
		WebElement nameField = driver.findElement(By.id("name"));
		WebElement emailField = driver.findElement(By.id("email"));
		WebElement addUserButton = driver.findElement(By.id("rowid"));
		
		nameField.sendKeys("user4");
		emailField.sendKeys("user4@mail.com");
		addUserButton.click();
		
		Thread.sleep(3000);
		
//		tableBody = driver.findElement(By.xpath("//table[@id='table1']//tbody"));
//		
//		rows = tableBody.findElements(By.xpath("./child::tr"));
//		
//		System.out.printf("Number of rows %d\n", rows.size());
//		
//		boolean userFound = false;
//		
//		for(WebElement row : rows) {
//			List<WebElement> cols = row.findElements(By.xpath("./child::td"));
//			if(cols.get(1).getText().equals("user4")) {
//				userFound = true;
//				break;
//			}
//		}
		
		//Equivalent to line number 92 to 106
		try {
			driver.findElement(By.xpath("//td[text()='user4']"));
		} catch(Exception e) {
			Assert.fail("User not found");
		}
		
		Thread.sleep(3000);
		
		//Assert.assertTrue(userFound, "User 4 Add is unsuccessful");
		
	}

	@AfterClass
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
